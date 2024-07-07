package com.yxinmiracle.ojweb.judg;

/*
 * @author  YxinMiracle
 * @date  2024-07-04 17:22
 * @Gitee: https://gitee.com/yxinmiracle
 */

import com.yxinmiracle.ojweb.model.entity.QuestionSubmit;

public interface JudgeService {

    QuestionSubmit doJudge(long questionSubmitId);
}
