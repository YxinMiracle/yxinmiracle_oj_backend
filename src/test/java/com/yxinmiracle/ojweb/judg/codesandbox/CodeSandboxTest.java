package com.yxinmiracle.ojweb.judg.codesandbox;

import com.yxinmiracle.ojweb.judg.codesandbox.impl.ExampleCodeSandboxImpl;
import com.yxinmiracle.ojweb.judg.codesandbox.impl.RemoteCodeSandboxImpl;
import com.yxinmiracle.ojweb.judg.codesandbox.model.ExecuteCodeRequest;
import com.yxinmiracle.ojweb.judg.codesandbox.model.ExecuteCodeResponses;
import com.yxinmiracle.ojweb.model.enums.QuestionSubmitLanguageEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/*
 * @author  YxinMiracle
 * @date  2024-07-04 13:47
 * @Gitee: https://gitee.com/yxinmiracle
 */

@SpringBootTest
class CodeSandboxTest {

    @Test
    void executeCode() {
        CodeSandbox codeSandbox = new RemoteCodeSandboxImpl();
        String code = "int main() {}";
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String> inputList = Arrays.asList("1 2", "3 4");
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .languages(language)
                .inputList(inputList)
                .build();
        ExecuteCodeResponses executeCodeResponses = codeSandbox.executeCode(executeCodeRequest);
        Assertions.assertNotNull(executeCodeResponses);
    }

    @Test
    void executeCode2() {
        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance("example");
        CodeSandBoxProxy codeSandBoxProxy = new CodeSandBoxProxy(codeSandbox);
        String code = "int main() {}";
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String> inputList = Arrays.asList("1 2", "3 4");
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .languages(language)
                .inputList(inputList)
                .build();
        ExecuteCodeResponses executeCodeResponses = codeSandBoxProxy.executeCode(executeCodeRequest);
        Assertions.assertNotNull(executeCodeResponses);
    }
}