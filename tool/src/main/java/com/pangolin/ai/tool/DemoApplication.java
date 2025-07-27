package com.pangolin.ai.tool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>文档地址：https://docs.langchain4j.info/integrations/language-models/open-ai</p>
 * <ul>
 *     <li>原生模式：自己构建ChatModel</li>
 *     <li>SpringBoot模式：通过application.properties自动配置，参考 ｛@link dev.langchain4j.openai.spring.AutoConfig｝</li>
 * </ul>
 **/
@SpringBootApplication(scanBasePackages = "com.pangolin.ai.tool")
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
