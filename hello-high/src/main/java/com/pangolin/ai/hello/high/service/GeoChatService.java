package com.pangolin.ai.hello.high.service;

import dev.langchain4j.service.SystemMessage;

public interface GeoChatService {
    @SystemMessage("你是一个地理百科全书")
    String chat(String question);
}
