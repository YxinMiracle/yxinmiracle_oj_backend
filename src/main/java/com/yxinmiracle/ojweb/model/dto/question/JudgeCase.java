package com.yxinmiracle.ojweb.model.dto.question;

/*
 * @author  YxinMiracle
 * @date  2024-07-02 16:54
 * @Gitee: https://gitee.com/yxinmiracle
 */

import lombok.Data;

@Data
public class JudgeCase {
    /**
     * 输入用例
     */
    private String input;

    /**
     * 输出用例
     */
    private String output;
}
