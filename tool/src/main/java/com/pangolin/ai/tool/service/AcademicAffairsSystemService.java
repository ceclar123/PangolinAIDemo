package com.pangolin.ai.tool.service;

import dev.langchain4j.service.SystemMessage;

public interface AcademicAffairsSystemService {
    @SystemMessage("你是学校教务系统")
    String chat(String question);
}
