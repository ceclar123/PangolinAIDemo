package com.pangolin.ai.tool.service;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

/**
 * 显示指定chatModel、tools，对应的必须是一个bean
 */
@AiService(wiringMode = AiServiceWiringMode.EXPLICIT, chatModel = "pureChatModel", tools = "studentInformaticaSystemTool")
public interface StudentInformaticaSystemService {
    @SystemMessage("你是学校教务系统")
    String chat(String question);
}
