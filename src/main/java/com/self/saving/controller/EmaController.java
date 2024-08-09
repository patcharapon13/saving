package com.self.saving.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.self.saving.service.EmaService;

@RestController
@RequestMapping("/Ema")
public class EmaController {

    @Autowired
    private EmaService EmaService;

    @GetMapping
    public String get() {
        return "Hello, Ema!";
    }
}
