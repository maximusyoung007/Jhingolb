package com.maximus.jhingolbback.controller;

import com.alibaba.fastjson.JSONObject;
import com.maximus.jhingolbback.service.TestService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TestConnectionController {
    private static final Logger logger = LogManager.getLogger("TestConnectionController");

    @Resource
    private TestService testService;

    @RequestMapping("testConnection")
    public String testConnection() {
        return "test Connection";
    }

    @RequestMapping("testMybatis")
    @ResponseBody
    public JSONObject testMybatis() {
        JSONObject outJson = new JSONObject();
        List<String> list = testService.getTestName();
        outJson.put("nameList",list);
        return outJson;
    }
}
