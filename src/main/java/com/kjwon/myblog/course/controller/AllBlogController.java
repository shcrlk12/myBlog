package com.kjwon.myblog.course.controller;

import com.kjwon.myblog.admin.dto.CategoryDto;
import com.kjwon.myblog.admin.service.CategoryService;
import com.kjwon.myblog.course.dto.CourseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AllBlogController {

    private final CategoryService courseService;

    @GetMapping("/blog/all")
    public String readAllBlog(Model model){

        List<CourseDto> list = courseService.findAllCourse();
        model.addAttribute("list", list);

        return "/blog/all";
    }
}
