package com.pangolin.ai.mcp.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * https://springdoc.cn/spring-ai/api/mcp/mcp-server-boot-starter-docs.html#_%E6%A0%87%E5%87%86_mcp_server
 */
@SpringBootApplication(scanBasePackages = "com.pangolin.ai.mcp.server")
public class McpServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(McpServerApplication.class, args);
    }
}
