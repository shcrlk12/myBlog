package com.kjwon.myblog.course.controller;


import com.kjwon.myblog.admin.dto.CategoryDto;
import com.kjwon.myblog.admin.service.CategoryService;
import com.kjwon.myblog.admin.service.CategoryServiceImpl;
import com.kjwon.myblog.course.dto.CommentDto;
import com.kjwon.myblog.course.dto.CourseDto;
import com.kjwon.myblog.course.model.CourseInput;
import com.kjwon.myblog.course.model.CourseParam;
import com.kjwon.myblog.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Controller
public class CourseController extends BaseController {
    private  static final org.apache.log4j.Logger log = Logger.getLogger(CategoryServiceImpl.class);

    private final CourseService courseService;
    private final CategoryService categoryService;

    @GetMapping("/course")
    public String course(Model model
            , CourseParam parameter, Principal principal) {

        List<CourseDto> list = courseService.frontList(parameter, principal.getName());
        model.addAttribute("list", list);

//        List<CategoryDto> categoryList = categoryService.frontList(principal.getName());
//
//        int totalCount = 0;
//        for (CategoryDto categoryDto : categoryList){
//            totalCount += categoryDto.getCourseCount();
//        }
//
//        model.addAttribute("courseTotalCount", totalCount);
//        model.addAttribute("categoryList", categoryList);

        return "course/index";
    }
    
    @GetMapping("/course/{id}")
    public String courseDetail(Model model
            , CourseParam parameter) {
        
        CourseDto detail = courseService.frontDetail(parameter.getId());
        System.out.println("detail URL");
        System.out.println(detail.getUrlFilename());
        model.addAttribute("detail", detail);

        List<CommentDto> commentDtos = courseService.getComments(parameter.getId());

        model.addAttribute("comments", commentDtos);

        log.info(commentDtos);
        return "course/detail";
    }

    @PostMapping("/course/{id}")
    public String comment(@PathVariable("id") Long blogId,
                          String commentText, Principal principal) {

        courseService.postComment(blogId, commentText, principal.getName());
        return "redirect:/course/" + blogId;
    }

    @GetMapping("/edit-blog-list")
    public String list(Model model, CourseParam parameter, Principal principal) {

        List<CourseDto> courseList = courseService.list(parameter, principal.getName());

        long totalCount = 0;
        if (!CollectionUtils.isEmpty(courseList)) {
            totalCount = courseList.get(0).getTotalCount();
        }
        String queryString = parameter.getQueryString();
        log.info(queryString);
        log.info(totalCount);

        String pagerHtml = getPaperHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);
        log.info(pagerHtml);

        model.addAttribute("list", courseList);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagerHtml);

        return "course/list";
    }



}
