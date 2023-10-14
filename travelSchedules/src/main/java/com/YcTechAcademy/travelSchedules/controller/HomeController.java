package com.YcTechAcademy.travelSchedules.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    /**
     * 메인 화면 조회
     */
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
