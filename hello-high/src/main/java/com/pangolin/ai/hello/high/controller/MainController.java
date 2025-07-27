package com.pangolin.ai.hello.high.controller;

import com.pangolin.ai.common.dto.ResultDto;
import com.pangolin.ai.hello.high.service.GeoChatService;
import com.pangolin.ai.hello.high.service.MedicalChatService;
import com.pangolin.ai.hello.high.service.MilitaryChatService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/hello")
public class MainController {
    @Autowired
    private GeoChatService geoChatService;
    @Autowired
    private MedicalChatService medicalChatService;
    @Autowired
    private MilitaryChatService militaryChatService;

    @GetMapping()
    public ResultDto<String> test() {
        return ResultDto.sucOf("hello world");
    }

    @GetMapping(value = "/test1")
    public ResultDto<String> test1(@RequestParam(value = "question", required = true) String question) {
        if (StringUtils.isBlank(question)) {
            return ResultDto.failedOf("question invalid");
        }

        String answer = geoChatService.chat(question.trim());
        log.info("test1 question={}, answer={}", question, answer);
        return ResultDto.sucOf(answer);
    }

    @GetMapping(value = "/test2")
    public ResultDto<String> test2(@RequestParam(value = "question", required = true) String question) {
        if (StringUtils.isBlank(question)) {
            return ResultDto.failedOf("question invalid");
        }

        String answer = medicalChatService.chat(question.trim());
        log.info("test2 question={}, answer={}", question, answer);
        return ResultDto.sucOf(answer);
    }

    @GetMapping(value = "/test3")
    public ResultDto<String> test3(@RequestParam(value = "question", required = true) String question) {
        if (StringUtils.isBlank(question)) {
            return ResultDto.failedOf("question invalid");
        }

        String answer = militaryChatService.chat(question.trim());
        log.info("test3 question={}, answer={}", question, answer);
        return ResultDto.sucOf(answer);
    }
}
