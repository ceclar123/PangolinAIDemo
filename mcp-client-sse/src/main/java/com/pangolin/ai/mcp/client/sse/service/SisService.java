package com.pangolin.ai.mcp.client.sse.service;

import dev.langchain4j.service.SystemMessage;

public interface SisService {
    @SystemMessage("你是学校教务系统")
    String chat(String question);
}
