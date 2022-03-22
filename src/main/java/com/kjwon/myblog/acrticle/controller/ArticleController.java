package com.kjwon.myblog.acrticle.controller;

import com.kjwon.myblog.acrticle.Dto.ArticleDto;
import com.kjwon.myblog.acrticle.entity.Article;
import com.kjwon.myblog.acrticle.service.ArticleService;
import com.kjwon.myblog.admin.dto.CategoryDto;
import com.kjwon.myblog.admin.service.CategoryService;
import com.kjwon.myblog.course.dto.CourseDto;
import com.kjwon.myblog.course.model.CourseInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

import static com.kjwon.myblog.util.ArticleUtil.articleOverview;

@RequiredArgsConstructor
@Controller
public class ArticleController {

    private final ArticleService articleService;
    private final CategoryService categoryService;

    @GetMapping("article/{articleType}")
    public String articleList(Model model,
                              @PathVariable("articleType") String articleType){

        List<ArticleDto> articleList = articleService.frontList(articleType);

        model.addAttribute("articleList", articleOverview(articleList, articleType));

        return "article/" + articleType;
    }

    @GetMapping("article/{articleType}/{id}")
    public String datailArticle(Model model,
                                @PathVariable("id") Long id){

        ArticleDto articleDto = articleService.detail(id);

        model.addAttribute("articleDto", articleDto);

        return "article/detail";
    }

    @GetMapping("article/write_article")
    public String writeArticle(Model model)
    {
        List<CategoryDto> categoryDtoList = categoryService.list();

        model.addAttribute("categoryList", categoryDtoList);

        return "article/write_article";
    }

    @PostMapping("article/write_article")
    public String postWriteArticle(Model model, HttpServletRequest request
            ,  Principal principal, ArticleDto articleDto) {

        System.out.println(articleDto.getCategoryId());
        System.out.println(articleDto.getContents());
        System.out.println(articleDto.getTitle());

        articleService.registerArticle(articleDto, principal.getName());
//        String saveFilename = "";
//        String urlFilename = "";
//
//        String basePath = "/usr/local/tomcat";
//        String baseLocalPath = "/img/blog/thumbnail/";
//        String baseUrlPath = "/img/blog/thumbnail/";
//
//        if(!Objects.equals(file.getOriginalFilename(), "")) {
//            String originalFilename = file.getOriginalFilename();
//
//
//
//            String[] arrFilename = getNewSaveFile(baseLocalPath, baseUrlPath, originalFilename);
//
//            saveFilename = arrFilename[0];
//            urlFilename = arrFilename[1];
//            log.info("blog thumnail save to " + basePath + baseLocalPath + originalFilename);
//
//            try {
//                File newFile = new File( basePath + baseLocalPath + originalFilename);
//                FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(newFile));
//            } catch (IOException e) {
//                log.info("############################ - 1");
//                log.info(e.getMessage());
//            }
//
//            parameter.setFilename(baseLocalPath + originalFilename);
//            parameter.setUrlFilename(baseLocalPath + originalFilename);
//        }
//        else{
//            parameter.setFilename(baseLocalPath + "default.jpg");
//            parameter.setUrlFilename(baseLocalPath + "default.jpg");
//        }
//
//
//
//        boolean editMode = request.getRequestURI().contains("/edit");
//
//        if (editMode) {
//            long id = parameter.getId();
//            CourseDto existCourse = courseService.getById(id);
//            if (existCourse == null) {
//                // error 처리
//                model.addAttribute("message", "강좌정보가 존재하지 않습니다.");
//                return "common/error";
//            }
//
//            boolean result = courseService.set(parameter, principal.getName());
//
//        } else {
//            boolean result = courseService.add(parameter, principal.getName());
//        }
//
        return "redirect:/";
    }
}
