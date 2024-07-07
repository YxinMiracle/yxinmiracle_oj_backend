package com.yxinmiracle.ojweb.judg;

/*
 * @author  YxinMiracle
 * @date  2024-07-04 20:46
 * @Gitee: https://gitee.com/yxinmiracle
 */

import com.yxinmiracle.ojweb.judg.strategy.JavaLanguageJudgeStrategyImpl;
import com.yxinmiracle.ojweb.judg.strategy.JudgeContext;
import com.yxinmiracle.ojweb.judg.strategy.JudgeStrategy;
import com.yxinmiracle.ojweb.judg.strategy.JudgeStrategyImpl;
import com.yxinmiracle.ojweb.judg.codesandbox.model.JudgeInfo;
import com.yxinmiracle.ojweb.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

@Service
public class JudgeManager {

    JudgeInfo doJudge(JudgeContext judgeContext){
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new JudgeStrategyImpl();
        if (language.equals("java")){
            judgeStrategy = new JavaLanguageJudgeStrategyImpl();
        }
        return judgeStrategy.doJudge(judgeContext);
    }

}
