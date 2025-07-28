package com.pangolin.ai.mcp.server.stdio.config;

import com.pangolin.ai.mcp.server.stdio.service.StudentInformaticaSystemService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ToolConfig {
    @Bean
    public ToolCallbackProvider sisTools(StudentInformaticaSystemService sisService) {
        return MethodToolCallbackProvider.builder().toolObjects(sisService).build();
    }
}
