package com.yxinmiracle.ojweb.judg.codesandbox;

/*
 * @author  YxinMiracle
 * @date  2024-07-04 17:05
 * @Gitee: https://gitee.com/yxinmiracle
 */

import com.yxinmiracle.ojweb.judg.codesandbox.model.ExecuteCodeRequest;
import com.yxinmiracle.ojweb.judg.codesandbox.model.ExecuteCodeResponses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@AllArgsConstructor
public class CodeSandBoxProxy implements CodeSandbox{

    private final CodeSandbox codeSandbox;

    @Override
    public ExecuteCodeResponses executeCode(ExecuteCodeRequest executeCodeRequest) {
        log.info("代码沙箱的请求信息："+executeCodeRequest.toString());
        ExecuteCodeResponses executeCodeResponses = codeSandbox.executeCode(executeCodeRequest);
        log.info("代码沙箱的响应信息: "+executeCodeResponses.toString());
        return executeCodeResponses;
    }
}
