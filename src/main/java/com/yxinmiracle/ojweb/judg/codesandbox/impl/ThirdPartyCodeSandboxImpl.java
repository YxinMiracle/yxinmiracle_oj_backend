package com.yxinmiracle.ojweb.judg.codesandbox.impl;

/*
 * @author  YxinMiracle
 * @date  2024-07-04 13:42
 * @Gitee: https://gitee.com/yxinmiracle
 */

import com.yxinmiracle.ojweb.judg.codesandbox.CodeSandbox;
import com.yxinmiracle.ojweb.judg.codesandbox.model.ExecuteCodeRequest;
import com.yxinmiracle.ojweb.judg.codesandbox.model.ExecuteCodeResponses;

/**
 * 第三方的代码沙箱
 */
public class ThirdPartyCodeSandboxImpl implements CodeSandbox {
    @Override
    public ExecuteCodeResponses executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("第三方的代码沙箱");
        return null;
    }
}
