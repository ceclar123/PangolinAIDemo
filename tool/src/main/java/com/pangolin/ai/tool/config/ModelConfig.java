package com.pangolin.ai.tool.config;

import com.pangolin.ai.tool.service.AcademicAffairsSystemService;
import com.pangolin.ai.tool.tool.StudentInformaticaSystemTool;
import dev.langchain4j.http.client.spring.restclient.SpringRestClientBuilder;
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
                .logRequests(Boolean.TRUE)
                .logResponses(Boolean.TRUE)
                .httpClientBuilder(new SpringRestClientBuilder())
                .build();
    }


    @Bean(name = "academicAffairsSystemService")
    public AcademicAffairsSystemService academicAffairsSystemService(ChatModel pureChatModel) {
        return AiServices.builder(AcademicAffairsSystemService.class)
                .chatModel(pureChatModel)
                .tools(new StudentInformaticaSystemTool())
                .build();
    }
}
