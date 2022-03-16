package com.kjwon.myblog.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String index(Model model) {


        /*
        String email = "satcop@naver.com";
        String subject = " 안녕하세요. 제로베이스 입니다. ";
        String text = "<p>안녕하세요.</p><p>반갑습니다.</p>";

        mailComponents.sendMail(email, subject, text);
        */

        return "index";
    }



    @RequestMapping("/error/denied")
    public String errorDenied() {

        return "error/denied";
    }
}
