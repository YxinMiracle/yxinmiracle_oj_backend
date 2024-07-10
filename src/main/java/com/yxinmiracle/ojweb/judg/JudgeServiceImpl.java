package com.yxinmiracle.ojweb.judg;

/*
 * @author  YxinMiracle
 * @date  2024-07-04 17:25
 * @Gitee: https://gitee.com/yxinmiracle
 */

import cn.hutool.json.JSONUtil;
import com.yxinmiracle.ojweb.common.ErrorCode;
import com.yxinmiracle.ojweb.exception.BusinessException;
import com.yxinmiracle.ojweb.judg.codesandbox.CodeSandBoxProxy;
import com.yxinmiracle.ojweb.judg.codesandbox.CodeSandbox;
import com.yxinmiracle.ojweb.judg.codesandbox.CodeSandboxFactory;
import com.yxinmiracle.ojweb.judg.codesandbox.model.ExecuteCodeRequest;
import com.yxinmiracle.ojweb.judg.codesandbox.model.ExecuteCodeResponses;
import com.yxinmiracle.ojweb.judg.strategy.JudgeContext;
import com.yxinmiracle.ojweb.model.dto.question.JudgeCase;
import com.yxinmiracle.ojweb.judg.codesandbox.model.JudgeInfo;
import com.yxinmiracle.ojweb.model.entity.Question;
import com.yxinmiracle.ojweb.model.entity.QuestionSubmit;
import com.yxinmiracle.ojweb.model.enums.QuestionSubmitLanguageEnum;
import com.yxinmiracle.ojweb.model.enums.QuestionSubmitStatusEnum;
import com.yxinmiracle.ojweb.service.QuestionService;
import com.yxinmiracle.ojweb.service.QuestionSubmitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JudgeServiceImpl implements JudgeService {

    @Resource
    private QuestionService questionService;

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Value("${codeSandbox.type}")
    private String type;

    @Resource
    private JudgeManager judgeManager;

    @Override
    public QuestionSubmit doJudge(long questionSubmitId) {
        QuestionSubmit questionSubmit = questionSubmitService.getById(questionSubmitId);
        if (questionSubmit == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"提交的题目信息不存在");
        }
        Long questionId  = questionSubmit.getQuestionId();
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "题目不存在");
        }
        if (!questionSubmit.getStatus().equals(QuestionSubmitStatusEnum.WAITING.getValue())){
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "题目已经在判题");
        }
        // 先更新状态
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean update = questionSubmitService.updateById(questionSubmitUpdate);
        if (!update){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "更新错误");
        }
        // 再放到代码沙箱

        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
        CodeSandBoxProxy codeSandBoxProxy = new CodeSandBoxProxy(codeSandbox);
        String code = questionSubmit.getCode();
        String judgeCaseStr = question.getJudgeCase();
        List<JudgeCase> judgeCaseList = JSONUtil.toList(judgeCaseStr, JudgeCase.class);
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .languages(language)
                .inputList(inputList)
                .build();
        ExecuteCodeResponses executeCodeResponses = codeSandBoxProxy.executeCode(executeCodeRequest);

        // 根据执行结果，判断题目的执行结果是否正确
        List<String> outputList = executeCodeResponses.getOutputList();

        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setJudgeInfo(executeCodeResponses.getJudgeInfo());
        judgeContext.setInputList(inputList);
        judgeContext.setOutputList(outputList);
        judgeContext.setQuestion(question);
        judgeContext.setJudgeCaseList(judgeCaseList);
        judgeContext.setQuestionSubmit(questionSubmit);
        JudgeInfo judgeInfo = judgeManager.doJudge(judgeContext);
//        judgeStrategy
        questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        update = questionSubmitService.updateById(questionSubmitUpdate);
        if (!update){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "更新错误");
        }
        QuestionSubmit questionSubmitResult = questionSubmitService.getById(questionSubmit.getId());
        return questionSubmitResult;
    }
}
