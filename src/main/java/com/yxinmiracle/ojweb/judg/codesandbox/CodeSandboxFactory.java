package com.yxinmiracle.ojweb.judg.codesandbox;

/*
 * @author  YxinMiracle
 * @date  2024-07-04 16:25
 * @Gitee: https://gitee.com/yxinmiracle
 */

import com.yxinmiracle.ojweb.judg.codesandbox.impl.ExampleCodeSandboxImpl;
import com.yxinmiracle.ojweb.judg.codesandbox.impl.RemoteCodeSandboxImpl;
import com.yxinmiracle.ojweb.judg.codesandbox.impl.ThirdPartyCodeSandboxImpl;

public class CodeSandboxFactory {

    public static CodeSandbox newInstance(String type) {
        switch (type) {
            case "example":
                return new ExampleCodeSandboxImpl();
            case "remote":
                return new RemoteCodeSandboxImpl();
            case "thirdParty":
                return new ThirdPartyCodeSandboxImpl();
            default:
                return new ExampleCodeSandboxImpl();
        }
    }

}
