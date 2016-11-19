package com.github.antego.autorship.music.adder.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {


    @RequestMapping("/")
    public String uploading() {
        return "index.html";
    }
}
