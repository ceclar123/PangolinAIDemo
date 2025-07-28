package com.pangolin.ai.mcp.client.stdio.config;

import com.pangolin.ai.mcp.client.stdio.service.SisService;
import dev.langchain4j.http.client.spring.restclient.SpringRestClientBuilder;
import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.mcp.client.DefaultMcpClient;
import dev.langchain4j.mcp.client.McpClient;
import dev.langchain4j.mcp.client.transport.McpTransport;
import dev.langchain4j.mcp.client.transport.stdio.StdioMcpTransport;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.Arrays;

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

    @Bean(name = "sisMcpClient")
    public McpClient sisMcpClient() {
        // 先将mcp-server-stdio打包成可执行文件
        // 这几个启动参数很重要,否则 dev.langchain4j.mcp.client.transport.stdio.ProcessIOHandler 获取到乱七八槽的数据，不是json格式，会报错
        McpTransport transport = new StdioMcpTransport.Builder()
                .command(Arrays.asList("java",
                        "-Dspring.ai.mcp.server.stdio=true",
                        "-Dspring.main.web-application-type=none",
                        "-Dlogging.pattern.console=",
                        "-jar",
                        "D:/html/mcp-server-stdio-1.0.0-SNAPSHOT.jar",
                        "--port=8080"))
                .logEvents(true)
                .build();
        return new DefaultMcpClient.Builder()
                .key("sis-mcp-client")
                .transport(transport)
                .initializationTimeout(Duration.ofMinutes(1))
                .build();
    }

    @Bean(name = "sisService")
    public SisService getSisService(ChatModel pureChatModel, McpClient mcpClient) {
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
