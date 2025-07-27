package com.pangolin.ai.hello.high.config;

import com.pangolin.ai.hello.high.service.GeoChatService;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelConfig {
    @Bean(name = "pureChatModel")
    public OpenAiChatModel getPureChatModel() {
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("DOUBAO_API_KEY"))
                .modelName("doubao-seed-1-6-250615")
                .baseUrl("https://ark.cn-beijing.volces.com/api/v3/")
                .build();
    }


    @Bean(name = "geoChatService")
    public GeoChatService getGeoChatService(ChatModel openAiChatModel) {
        return AiServices.create(GeoChatService.class, openAiChatModel);
    }
}
