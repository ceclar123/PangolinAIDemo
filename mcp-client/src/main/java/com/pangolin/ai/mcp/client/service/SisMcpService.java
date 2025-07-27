package com.pangolin.ai.mcp.client.service;

import dev.langchain4j.service.SystemMessage;

public interface SisMcpService {
    @SystemMessage("你是学校教务系统")
    String chat(String question);
}
