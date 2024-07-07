package com.yxinmiracle.ojweb.judg.codesandbox.impl;

/*
 * @author  YxinMiracle
 * @date  2024-07-04 13:42
 * @Gitee: https://gitee.com/yxinmiracle
 */

import com.yxinmiracle.ojweb.judg.codesandbox.CodeSandbox;
import com.yxinmiracle.ojweb.judg.codesandbox.model.ExecuteCodeRequest;
import com.yxinmiracle.ojweb.judg.codesandbox.model.ExecuteCodeResponses;
import com.yxinmiracle.ojweb.judg.codesandbox.model.JudgeInfo;
import com.yxinmiracle.ojweb.model.enums.JudgeInfoMessageEnum;
import com.yxinmiracle.ojweb.model.enums.QuestionSubmitStatusEnum;

import java.util.List;

/**
 * 示例代码沙箱
 */
public class ExampleCodeSandboxImpl implements CodeSandbox {
    @Override
    public ExecuteCodeResponses executeCode(ExecuteCodeRequest executeCodeRequest) {
        List<String> inputList = executeCodeRequest.getInputList();

        ExecuteCodeResponses executeCodeResponses = new ExecuteCodeResponses();
        executeCodeResponses.setOutputList(inputList);
        executeCodeResponses.setMessage("测试执行成功");
        executeCodeResponses.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());

        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getText());
        judgeInfo.setMemory(100L);
        judgeInfo.setTime(100L);
        executeCodeResponses.setJudgeInfo(judgeInfo);

        return executeCodeResponses;
    }
}
