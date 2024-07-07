package com.yxinmiracle.ojweb.judg.strategy;

/*
 * @author  YxinMiracle
 * @date  2024-07-04 20:10
 * @Gitee: https://gitee.com/yxinmiracle
 */

import com.yxinmiracle.ojweb.model.dto.question.JudgeCase;
import com.yxinmiracle.ojweb.judg.codesandbox.model.JudgeInfo;
import com.yxinmiracle.ojweb.model.entity.Question;
import com.yxinmiracle.ojweb.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/*
用于定义在策略中的前提参数
 */
@Data
public class JudgeContext {
    private JudgeInfo judgeInfo;

    private List<String> inputList;

    private List<String> outputList;

    private Question question;

    private List<JudgeCase> judgeCaseList;

    private QuestionSubmit questionSubmit;
}
