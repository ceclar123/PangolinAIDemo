package com.pangolin.ai.hello.low.controller;

import com.pangolin.ai.common.dto.ResultDto;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.openai.OpenAiChatModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/hello")
public class MainController {
    @Qualifier("pureChatModel")
    @Autowired
    private ChatModel chatModel;

    /**
     * application.properties中自动配置
     */
    @Autowired
    private OpenAiChatModel openAiChatModel;

    @GetMapping()
    public ResultDto<String> test() {
        return ResultDto.sucOf("hello world");
    }

    @GetMapping(value = "/test1")
    public ResultDto<String> test1(@RequestParam(value = "question", required = true) String question) {
        if (StringUtils.isBlank(question)) {
            return ResultDto.failedOf("question invalid");
        }

        String answer = chatModel.chat(question.trim());
        log.info("test1 question={}, answer={}", question, answer);
        return ResultDto.sucOf(answer);
    }

    @GetMapping(value = "/test11")
    public ResultDto<String> test11(@RequestParam(value = "question", required = true) String question) {
        if (StringUtils.isBlank(question)) {
            return ResultDto.failedOf("question invalid");
        }

        UserMessage message = UserMessage.userMessage(question.trim());
        ChatResponse answer = chatModel.chat(message);
        log.info("test11 question={}, text={}, token={}", question, answer.aiMessage().text(), answer.metadata().tokenUsage().toString());
        return ResultDto.sucOf(answer.aiMessage().text());
    }

    @GetMapping(value = "/test2")
    public ResultDto<String> test2(@RequestParam(value = "question", required = true) String question) {
        if (StringUtils.isBlank(question)) {
            return ResultDto.failedOf("question invalid");
        }

        String answer = openAiChatModel.chat(question.trim());
        log.info("test2 question={}, answer={}", question, answer);
        return ResultDto.sucOf(answer);
    }

    @GetMapping(value = "/test21")
    public ResultDto<String> test21(@RequestParam(value = "question", required = true) String question) {
        if (StringUtils.isBlank(question)) {
            return ResultDto.failedOf("question invalid");
        }

        UserMessage message = UserMessage.builder().addContent(TextContent.from(question.trim())).build();
        ChatResponse answer = openAiChatModel.chat(message);
        log.info("test21 question={}, text={}, token={}", question, answer.aiMessage().text(), answer.metadata().tokenUsage().toString());
        return ResultDto.sucOf(answer.aiMessage().text());
    }
}
