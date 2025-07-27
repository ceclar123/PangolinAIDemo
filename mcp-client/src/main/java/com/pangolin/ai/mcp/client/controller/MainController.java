package com.pangolin.ai.mcp.client.controller;

import com.pangolin.ai.common.dto.ResultDto;
import com.pangolin.ai.mcp.client.service.SisMcpService;
import io.modelcontextprotocol.client.McpAsyncClient;
import io.modelcontextprotocol.client.McpSyncClient;
import io.modelcontextprotocol.spec.McpSchema;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/hello")
public class MainController {
    @Autowired
    private SisMcpService sisMcpService;

    @Autowired
    private List<McpSyncClient> mcpSyncClients;

    @Autowired
    private List<McpAsyncClient> mcpAsyncClients;

    @GetMapping()
    public ResultDto<String> test() {
        return ResultDto.sucOf("hello world");
    }

    @GetMapping(value = "/test1")
    public ResultDto<String> test1(@RequestParam(value = "question", required = true) String question) {
        if (StringUtils.isBlank(question)) {
            return ResultDto.failedOf("question invalid");
        }



        String answer = sisMcpService.chat(question);

        log.info("test1 question={}, answer={}", question, answer);
        return ResultDto.sucOf(answer);
    }

}
