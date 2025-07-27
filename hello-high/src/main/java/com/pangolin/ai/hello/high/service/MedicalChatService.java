package com.pangolin.ai.hello.high.service;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

/**
 * 显示指定ModelConfig中配置的模型
 **/
@AiService(wiringMode = EXPLICIT, chatModel = "pureChatModel")
public interface MedicalChatService {
    @SystemMessage("你是一个医学百科全书")
    String chat(String question);
}
