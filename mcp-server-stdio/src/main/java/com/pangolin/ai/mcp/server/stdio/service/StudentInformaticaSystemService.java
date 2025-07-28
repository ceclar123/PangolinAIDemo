package com.pangolin.ai.mcp.server.stdio.service;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StudentInformaticaSystemService {

    @Tool(description = "获取科目考试成绩")
    public String getSubjectNumber(@ToolParam(description = "学号", required = true) String studentId, @ToolParam(description = "科目", required = true) String subject) {
        if (StringUtils.isBlank(subject)) {
            JSONObject jo = new JSONObject();
            jo.put("subject", subject);
            jo.put("score", "未知");
            return jo.toJSONString();
        }

        Integer score = RandomUtils.secure().randomInt(0, 101);
        log.info("getSubjectNumber studentId={}, subject={}, score={}", studentId, subject, score);

        JSONObject jo = new JSONObject();
        jo.put("subject", subject);
        jo.put("score", score);

        return jo.toJSONString();
    }
}
