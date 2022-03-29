package com.kjwon.myblog.acrticle.controller;

import com.kjwon.myblog.acrticle.Dto.ArticleDto;
import com.kjwon.myblog.acrticle.Dto.LikeUnlikeDto;
import com.kjwon.myblog.acrticle.entity.Article;
import com.kjwon.myblog.acrticle.entity.ArticleLike;
import com.kjwon.myblog.acrticle.entity.ArticleUnlike;
import com.kjwon.myblog.acrticle.service.ArticleService;
import com.kjwon.myblog.admin.dto.CategoryDto;
import com.kjwon.myblog.admin.service.CategoryService;
import com.kjwon.myblog.course.dto.CommentDto;
import com.kjwon.myblog.course.dto.CourseDto;
import com.kjwon.myblog.course.model.CourseInput;
import com.kjwon.myblog.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
import java.util.Optional;

import static com.kjwon.myblog.util.ArticleUtil.articleOverview;
import static com.kjwon.myblog.util.ArticleUtil.pagingUtil;

@RequiredArgsConstructor
@Controller
public class ArticleController {

    private final ArticleService articleService;
    private final CategoryService categoryService;

//    @GetMapping("article/{articleType}")
//    public String articleList(Model model,
//                              @PathVariable("articleType") String articleType){
//
//        System.out.println(articleType);
//        List<ArticleDto> articleList = articleService.frontList(articleType);
//
//        model.addAttribute("articleList", articleOverview(articleList, articleType));
//
//        return "article/articleList";
//    }

    @GetMapping("article/{articleType}/{id}")
    public String datailArticle(Model model,
                                @PathVariable("id") Long id, @PathVariable("articleType") String articleType,
                                Optional<Integer> page){
        int pageNum = 1;

        if(page.isPresent())
            pageNum = page.get();

        if(id == 0){
            Page<ArticleDto> articleList = articleService.frontList(pageNum, articleType);

            model.addAttribute("articleList", articleOverview(articleList));

            model.addAttribute("paging", pagingUtil(articleList));

            return "article/articleList";
        }

        ArticleDto articleDto = articleService.detail(id);
        model.addAttribute("articleDto", articleDto);

        List<CommentDto> commentDtos = articleService.getComments(id);
        model.addAttribute("comments", commentDtos);

        LikeUnlikeDto likeUnlikeDto = articleService.getLikeUnLike(id);
        model.addAttribute("likeUnlike", likeUnlikeDto);

        return "article/detail";
    }

    @PostMapping("article/{articleType}/deleteComment")
    public String deleteComment(Long commentId, @PathVariable String articleType){

        System.out.println(commentId);

        articleService.deleteComment(commentId);
        return "redirect:/article/" + articleType;
    }


    @PostMapping("article/{articleType}/{id}")
    public String registerComment(Model model,
                                @PathVariable("id") Long id, CommentDto commentDto, Principal principal){

        articleService.registerComment(id, commentDto, principal.getName());
        model.addAttribute("id", id);
        return "redirect:/article/detail/" + id;
    }

    @PostMapping("article/{articleType}/{id}/like")
    public String articleLike(Model model,
                              @PathVariable Long id, @PathVariable String articleType, String likeUser, Principal principal){

        if(likeUser.equals(principal.getName()))
            articleService.articleLike(id, likeUser);

        LikeUnlikeDto likeUnlikeDto = articleService.getLikeUnLike(id);

        if(likeUnlikeDto.getLike() - likeUnlikeDto.getUnlike() > 2)
            articleService.postPopularArticle(id);
        return "redirect:/article/" + articleType + "/" + id;
    }

    @PostMapping("article/{articleType}/{id}/unlike")
    public String articleUnlike(Model model,
                              @PathVariable Long id, @PathVariable String articleType, String likeUser, Principal principal){

        if(likeUser.equals(principal.getName()))
            articleService.articleUnlike(id, likeUser);

        return "redirect:/article/" + articleType + "/" + id;
    }

    @GetMapping(value = {"article/write_article", "/article/{articleType}/edit"})
    public String writeArticle(Model model, Long id)
    {

        List<CategoryDto> categoryDtoList = categoryService.list();
        ArticleDto articleDto = new ArticleDto();

        model.addAttribute("categoryList", categoryDtoList);

        if(id == null) {
            model.addAttribute("edit", false);
            model.addAttribute("articleDto", articleDto);
            return "article/write_article";

        }


        articleDto = articleService.detail(id);
        model.addAttribute("edit", true);
        model.addAttribute("articleDto", articleDto);

        return "article/write_article";

    }


    @PostMapping(value = {"article/write_article", "/article/{articleType}/edit"})
    public String postWriteArticle(Model model, HttpServletRequest request
            ,  Principal principal, ArticleDto articleDto) {

        if(request.getRequestURI().contains("edit"))
        {
            articleService.updateArticle(articleDto, principal.getName());
        }else{
            articleService.registerArticle(articleDto, principal.getName());
        }

        return "redirect:/";
    }
}
