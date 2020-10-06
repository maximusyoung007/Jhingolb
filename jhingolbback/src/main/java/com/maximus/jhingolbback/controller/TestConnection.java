package com.maximus.jhingolbback.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestConnection {
    @RequestMapping("testConnection")
    public String testConnection() {
        return "test Connection";
    }
}
