package com.yxinmiracle.ojweb.judg.codesandbox;

/*
 * @author  YxinMiracle
 * @date  2024-07-04 13:34
 * @Gitee: https://gitee.com/yxinmiracle
 */

import com.yxinmiracle.ojweb.judg.codesandbox.model.ExecuteCodeRequest;
import com.yxinmiracle.ojweb.judg.codesandbox.model.ExecuteCodeResponses;

public interface CodeSandbox {

    ExecuteCodeResponses executeCode(ExecuteCodeRequest executeCodeRequest);
}
