package com.demo.learnspringbootsecurity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping(value = "/")
    public String home() {
        return "index";
    }

    @GetMapping(value = "/hello")
    public String hello() {
        return "hello";
    }
}
