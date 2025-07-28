package com.pangolin.ai.mcp.client.stdio.controller;

import com.alibaba.fastjson2.JSONObject;
import com.pangolin.ai.common.dto.ResultDto;
import com.pangolin.ai.mcp.client.stdio.service.SisService;
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
    private SisService sisService;

    @Autowired
    private List<McpSyncClient> mcpSyncClients;

//    @Autowired
//    private List<McpAsyncClient> mcpAsyncClients;

    @GetMapping()
    public ResultDto<String> test() {
        return ResultDto.sucOf("hello world");
    }

    /**
     * 单一的调用mcp server，需要自己组装参数，相当于一次http调用
     *
     * @param question 问题
     * @return
     */
    @GetMapping(value = "/test1")
    public ResultDto<String> test1(@RequestParam(value = "question", required = true) String question) {
        if (StringUtils.isBlank(question)) {
            return ResultDto.failedOf("question invalid");
        }

        log.info("test1 question={}, size={}, ping={}", question, mcpSyncClients.size(), mcpSyncClients.getFirst().ping());
        for (McpSchema.Tool tool : mcpSyncClients.getFirst().listTools().tools()) {
            log.info("test1 tool name={}, desc={}", tool.name(), tool.description());
        }

        JSONObject jo = new JSONObject();
        jo.put("subject", "语文");
        jo.put("studentId", "1002");

        for (McpSyncClient mcpSyncClient : mcpSyncClients) {
            for (McpSchema.Tool tool : mcpSyncClient.listTools().tools()) {
                McpSchema.CallToolRequest request = new McpSchema.CallToolRequest(tool.name(), jo.toJSONString());
                McpSchema.CallToolResult result = mcpSyncClient.callTool(request);
                log.info("test1 invoke tool name={}, desc={}, result={}", tool.name(), tool.description(), result.content());
            }
        }

        return ResultDto.sucOf("ok");
    }

    /**
     * 使用langchain4j结合模型调用mcp server
     *
     * @param question 问题
     * @return
     */
    @GetMapping(value = "/test2")
    public ResultDto<String> test2(@RequestParam(value = "question", required = true) String question) {
        if (StringUtils.isBlank(question)) {
            return ResultDto.failedOf("question invalid");
        }

        String answer = sisService.chat(question);

        log.info("test2 question={}, answer={}", question, answer);
        return ResultDto.sucOf(answer);
    }
}
