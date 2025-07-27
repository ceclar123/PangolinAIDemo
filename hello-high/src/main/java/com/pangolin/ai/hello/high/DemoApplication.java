package com.pangolin.ai.hello.high;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>文档地址：https://docs.langchain4j.info/tutorials/ai-services#ai-services-1</p>
 * <p>https://docs.langchain4j.info/tutorials/spring-boot-integration#%E5%A3%B0%E6%98%8E%E5%BC%8F-ai-%E6%9C%8D%E5%8A%A1%E7%9A%84-spring-boot-%E5%90%AF%E5%8A%A8%E5%99%A8</p>
 * <ul>
 *     <li>原生模式：自己通过AiServices.create</li>
 *     <li>SpringBoot模式：通过@AiService注册自动实现</li>
 * </ul>
 **/
@SpringBootApplication(scanBasePackages = "com.pangolin.ai.hello.high")
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
