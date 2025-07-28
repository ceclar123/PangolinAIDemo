package com.pangolin.ai.mcp.client.sse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * https://springdoc.cn/spring-ai/api/mcp/mcp-client-boot-starter-docs.html
 */
@SpringBootApplication(scanBasePackages = "com.pangolin.ai.mcp.client.sse")
public class McpClientSseApplication {
    public static void main(String[] args) {
        SpringApplication.run(McpClientSseApplication.class, args);
    }
}
