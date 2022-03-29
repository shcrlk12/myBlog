package com.kjwon.myblog.main.controller;


import com.kjwon.myblog.admin.entity.Banner;
import com.kjwon.myblog.admin.service.BannerService;
import com.kjwon.myblog.components.MailComponents;
import com.kjwon.myblog.course.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final MailComponents mailComponents;
    private final BannerService bannerService;

    @RequestMapping("/")
    public String index(Model model) {

        List<Banner> banner = bannerService.mainPage();
        model.addAttribute("banner", banner);
        
        return "index";
    }
    
    
    
    @RequestMapping("/error/denied")
    public String errorDenied() {
        
        return "error/denied";
    }
    
    
    
}
