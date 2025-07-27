package com.pangolin.ai.tool.controller;

import com.pangolin.ai.common.dto.ResultDto;
import com.pangolin.ai.tool.service.AcademicAffairsSystemService;
import com.pangolin.ai.tool.service.StudentInformaticaSystemService;
import com.pangolin.ai.tool.tool.StudentInformaticaSystemTool;
import dev.langchain4j.agent.tool.ToolExecutionRequest;
import dev.langchain4j.agent.tool.ToolSpecification;
import dev.langchain4j.agent.tool.ToolSpecifications;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.ToolExecutionResultMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.request.ToolChoice;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.service.tool.DefaultToolExecutor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/hello")
public class MainController {
    @Qualifier("pureChatModel")
    @Autowired
    private ChatModel chatModel;
    @Autowired
    private AcademicAffairsSystemService academicAffairsSystemService;
    @Autowired
    private StudentInformaticaSystemService studentInformaticaSystemService;

    @GetMapping()
    public ResultDto<String> test() {
        return ResultDto.sucOf("hello world");
    }

    @GetMapping(value = "/test1")
    public ResultDto<String> test1(@RequestParam(value = "question", required = true) String question) {
        if (StringUtils.isBlank(question)) {
            return ResultDto.failedOf("question invalid");
        }

        StudentInformaticaSystemTool tool = new StudentInformaticaSystemTool();
        List<ToolSpecification> toolSpecifications = ToolSpecifications.toolSpecificationsFrom(StudentInformaticaSystemTool.class);

        SystemMessage systemMessage = SystemMessage.systemMessage("你是学校教务系统");
        UserMessage userMessage = UserMessage.userMessage(question);

        ChatRequest request = ChatRequest.builder()
                .messages(Arrays.asList(systemMessage, userMessage))
                .toolSpecifications(toolSpecifications)
                .toolChoice(ToolChoice.AUTO)
                .build();
        ChatResponse answer = chatModel.chat(request);

        log.info("test1 question={}, hasToolExecutionRequests={}, text={}, token={}", question, answer.aiMessage().hasToolExecutionRequests(),
                answer.aiMessage().text(), answer.metadata().tokenUsage().toString());
        if (answer.aiMessage().hasToolExecutionRequests()) {
            for (ToolExecutionRequest toolExecutionRequest : answer.aiMessage().toolExecutionRequests()) {
                String result = new DefaultToolExecutor(tool, toolExecutionRequest).execute(toolExecutionRequest, null);
                if (StringUtils.isBlank(result)) {
                    log.warn("test1 toolInvoke 结果为空 ignore , name={}", toolExecutionRequest.name());
                    continue;
                } else {
                    // 将工具计算结果上报个模型，汇总结果
                    request = ChatRequest.builder()
                            .messages(Arrays.asList(systemMessage, userMessage, ToolExecutionResultMessage.from(toolExecutionRequest.id(), toolExecutionRequest.name(), result)))
                            .build();
                    ChatResponse response = chatModel.chat(request);
                    log.info("test1 工具结果汇总 question={}, text={}, token={}", response, response.aiMessage().text(), response.metadata().tokenUsage().toString());
                    return ResultDto.sucOf(response.aiMessage().text());
                }
            }
        }

        return ResultDto.sucOf(answer.aiMessage().text());
    }

    @GetMapping(value = "/test2")
    public ResultDto<String> test2(@RequestParam(value = "question", required = true) String question) {
        if (StringUtils.isBlank(question)) {
            return ResultDto.failedOf("question invalid");
        }

        String answer = academicAffairsSystemService.chat(question.trim());
        log.info("test2 question={}, answer={}", question, answer);
        return ResultDto.sucOf(answer);
    }

    @GetMapping(value = "/test3")
    public ResultDto<String> test3(@RequestParam(value = "question", required = true) String question) {
        if (StringUtils.isBlank(question)) {
            return ResultDto.failedOf("question invalid");
        }

        String answer = studentInformaticaSystemService.chat(question.trim());
        log.info("test3 question={}, answer={}", question, answer);
        return ResultDto.sucOf(answer);
    }
}
