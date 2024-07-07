package com.yxinmiracle.ojweb.judg.codesandbox.model;

/*
 * @author  YxinMiracle
 * @date  2024-07-02 17:06
 * @Gitee: https://gitee.com/yxinmiracle
 */

import lombok.Data;

@Data
public class JudgeInfo {

    /**
     * 程序执行信息
     */
    private String message;

    /**
     * 消耗的内存
     */
    private Long memory;

    /**
     * 消耗的时间
     */
    private Long time;

}