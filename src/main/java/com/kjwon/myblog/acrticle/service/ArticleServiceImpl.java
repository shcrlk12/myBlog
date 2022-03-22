package com.kjwon.myblog.acrticle.service;

import com.kjwon.myblog.acrticle.Dto.ArticleDto;
import com.kjwon.myblog.acrticle.entity.Article;
import com.kjwon.myblog.acrticle.repository.ArticleRepository;
import com.kjwon.myblog.admin.entity.Category;
import com.kjwon.myblog.admin.repository.CategoryRepository;
import com.kjwon.myblog.member.entity.Member;
import com.kjwon.myblog.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ArticleServiceImpl implements ArticleService{

    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;
    private final ArticleRepository articleRepository;

    @Override
    public void registerArticle(ArticleDto articleDto, String name) {

        Member member = memberRepository.getById(name);
        Category category = categoryRepository.getById(articleDto.getCategoryId());

        Article article = Article.builder()
                .title(articleDto.getTitle())
                .contents("articleDto.getContents()")
                .isDelete(false)
                .regDt(LocalDateTime.now())
                .member(member)
                .category(category)
                .build();

        articleRepository.save(article);
    }
}
