package com.pangolin.ai.mcp.server.stdio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * https://springdoc.cn/spring-ai/api/mcp/mcp-server-boot-starter-docs.html#_%E6%A0%87%E5%87%86_mcp_server
 */
@SpringBootApplication(scanBasePackages = "com.pangolin.ai.mcp.server.stdio")
public class McpServerStdioApplication {
    public static void main(String[] args) {
        SpringApplication.run(McpServerStdioApplication.class, args);
    }
}
