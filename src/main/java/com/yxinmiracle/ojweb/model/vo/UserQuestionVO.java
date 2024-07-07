package com.yxinmiracle.ojweb.model.vo;

/*
 * @author  YxinMiracle
 * @date  2024-07-03 19:39
 * @Gitee: https://gitee.com/yxinmiracle
 */

import com.yxinmiracle.ojweb.model.dto.question.JudgeCase;
import com.yxinmiracle.ojweb.model.dto.question.JudgeConfig;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class UserQuestionVO implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签列表（json 数组）
     */
    private List<String> tags;

    /**
     * 题目答案
     */
    private String answer;

    /**
     * 题目提交数
     */
    private Integer submitNum;

    /**
     * 题目通过数
     */
    private Integer acceptedNum;

    /**
     * 判题用例（json 数组，一个输入对应于一个输出）
     */
    private List<JudgeCase> judgeCase;

    /**
     * 判题配置（json 对象、时间限制、内存限制）
     */
    private JudgeConfig judgeConfig;


    private static final long serialVersionUID = 1L;

}
