package com.kjwon.myblog.admin.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminMainController {
    
    
    @GetMapping("/admin/main.do")
    public String main() {
        
        return "admin/main";
    }

    @GetMapping("/admin/blog/default.do")
    public String blogDefaultImageChange(){
        return "admin/blog-default-img";
    }

    
    
    
}
