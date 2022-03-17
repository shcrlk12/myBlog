package com.kjwon.myblog.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String index(Model model) {
        return "index";
    }



    @RequestMapping("/error/denied")
    public String errorDenied() {

        return "error/denied";
    }
}
