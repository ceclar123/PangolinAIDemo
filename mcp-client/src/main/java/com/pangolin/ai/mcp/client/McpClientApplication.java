package com.pangolin.ai.mcp.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * https://springdoc.cn/spring-ai/api/mcp/mcp-client-boot-starter-docs.html
 */
@SpringBootApplication(scanBasePackages = "com.pangolin.ai.mcp.client")
public class McpClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(McpClientApplication.class, args);
    }
}
