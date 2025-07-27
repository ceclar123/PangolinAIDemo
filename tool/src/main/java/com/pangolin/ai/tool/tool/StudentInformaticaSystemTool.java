package com.pangolin.ai.tool.tool;

import com.alibaba.fastjson2.JSONObject;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component(value = "studentInformaticaSystemTool")
public class StudentInformaticaSystemTool {
    @Tool(value = "获取科目考试成绩")
    public String getSubjectNumber(@P(value = "学号", required = true) String studentId, @P(value = "科目", required = true) String subject) {
        if (StringUtils.isBlank(subject)) {
            JSONObject jo = new JSONObject();
            jo.put("subject", subject);
            jo.put("number", "未知");
            return jo.toJSONString();
        }

        Integer number = RandomUtils.secure().randomInt(0, 101);
        log.info("getSuperLottoNextNumber subject={}, number={}", subject, number);

        JSONObject jo = new JSONObject();
        jo.put("subject", subject);
        jo.put("number", number);

        return jo.toJSONString();
    }
}
