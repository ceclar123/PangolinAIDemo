package com.pangolin.ai.mcp.client.sse.config;

import com.pangolin.ai.mcp.client.sse.service.SisService;
import dev.langchain4j.http.client.spring.restclient.SpringRestClientBuilder;
import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.mcp.client.DefaultMcpClient;
import dev.langchain4j.mcp.client.McpClient;
import dev.langchain4j.mcp.client.transport.McpTransport;
import dev.langchain4j.mcp.client.transport.http.HttpMcpTransport;
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
                .httpClientBuilder(new SpringRestClientBuilder())
                .build();
    }

    @Bean(name = "sisService")
    public SisService getSisService(ChatModel pureChatModel) {
        McpTransport transport = new HttpMcpTransport.Builder()
                .sseUrl("http://localhost:8000/sse")
                .logRequests(true)
                .logResponses(true)
                .build();
        McpClient mcpClient = new DefaultMcpClient.Builder()
                .key("sis-mcp-client")
                .transport(transport)
                .build();

        McpToolProvider toolProvider = McpToolProvider.builder()
                .mcpClients(mcpClient)
                //.filterToolNames("get_issue", "get_issue_comments", "list_issues")
                .build();

        return AiServices.builder(SisService.class)
                .chatModel(pureChatModel)
                .toolProvider(toolProvider)
                .build();
    }
}
