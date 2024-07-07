package com.yxinmiracle.ojweb.judg.codesandbox.model;

/*
 * @author  YxinMiracle
 * @date  2024-07-04 13:36
 * @Gitee: https://gitee.com/yxinmiracle
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteCodeRequest {

    private List<String> inputList;

    private String code;

    private String languages;

}
