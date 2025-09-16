package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hr")
public class HRController {

    @GetMapping("/test")
    public String testHR() {
        return "Hello HR!";
    }
}
