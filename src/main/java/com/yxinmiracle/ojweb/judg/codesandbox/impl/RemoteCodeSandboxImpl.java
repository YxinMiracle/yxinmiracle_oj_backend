package com.yxinmiracle.ojweb.judg.codesandbox.impl;

/*
 * @author  YxinMiracle
 * @date  2024-07-04 13:42
 * @Gitee: https://gitee.com/yxinmiracle
 */

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.yxinmiracle.ojweb.common.ErrorCode;
import com.yxinmiracle.ojweb.exception.BusinessException;
import com.yxinmiracle.ojweb.judg.codesandbox.CodeSandbox;
import com.yxinmiracle.ojweb.judg.codesandbox.model.ExecuteCodeRequest;
import com.yxinmiracle.ojweb.judg.codesandbox.model.ExecuteCodeResponses;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

/**
 * 远程代码沙箱
 */
public class RemoteCodeSandboxImpl implements CodeSandbox {
    private static final String AUTH_HEADER = "auth";

    private static final String AUTH_REQUEST_SECRET = "secretKey";

    @Value("${codeSandbox.ip}")
    private String ip;

    @Value("${codeSandbox.port}")
    private String port;

    @Override
    public ExecuteCodeResponses executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("远程代码沙箱");
        String url = "http://"+ip+":"+port+"/executeCode";
        String json = JSONUtil.toJsonStr(executeCodeRequest);
        String responseStr = HttpUtil.createPost(url)
                .header(AUTH_HEADER, AUTH_REQUEST_SECRET)
                .body(json)
                .execute()
                .body();
        if (StringUtils.isBlank(responseStr)){
            throw new BusinessException(ErrorCode.API_REQUEST_ERROR,"execute error,message:"+responseStr);
        }
        return JSONUtil.toBean(responseStr, ExecuteCodeResponses.class);
    }
}
