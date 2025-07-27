package com.pangolin.ai.hello.high.service;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;

/**
 * 自动模式，会自行查询已配置的模型，参考
 * <ul>
 *     <li>dev.langchain4j.service.spring.AiServicesAutoConfig#aiServicesRegisteringBeanFactoryPostProcessor()</li>
 *     <li>dev.langchain4j.service.spring.AiServicesAutoConfig#addBeanReference</li>
 * </ul>
 */
@AiService
public interface MilitaryChatService {
    @SystemMessage("你是一个军事百科全书")
    String chat(String question);
}
