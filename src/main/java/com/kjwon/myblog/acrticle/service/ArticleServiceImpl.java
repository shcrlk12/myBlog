package com.kjwon.myblog.acrticle.service;

import com.kjwon.myblog.acrticle.Dto.ArticleDto;
import com.kjwon.myblog.acrticle.entity.Article;
import com.kjwon.myblog.acrticle.repository.ArticleRepository;
import com.kjwon.myblog.admin.entity.Category;
import com.kjwon.myblog.admin.repository.CategoryRepository;
import com.kjwon.myblog.course.dto.CommentDto;
import com.kjwon.myblog.course.entity.Comment;
import com.kjwon.myblog.course.entity.Course;
import com.kjwon.myblog.course.repository.CommentRepository;
import com.kjwon.myblog.member.entity.Member;
import com.kjwon.myblog.member.repository.MemberRepository;
import com.kjwon.myblog.util.ArticleUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ArticleServiceImpl implements ArticleService{

    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    @Override
    public void registerArticle(ArticleDto articleDto, String name) {

        Member member = memberRepository.getById(name);
        Category category = categoryRepository.getById(articleDto.getCategoryId());

        Article article = Article.builder()
                .title(articleDto.getTitle())
                .contents(articleDto.getContents())
                .isDelete(false)
                .regDt(LocalDateTime.now())
                .member(member)
                .category(category)
                .build();

        articleRepository.save(article);
    }

    @Override
    public List<ArticleDto> frontList(String articleType) {

        Optional<Category> articleOptional = categoryRepository.findByArticlePath(articleType);

        List<Article> articleList = articleRepository.findByArticleOnCategory(articleOptional.get().getCategoryName());

        return ArticleDto.of(articleList);

    }

    @Override
    public ArticleDto detail(Long id) {

        Optional<Article> articleOptional = articleRepository.findById(id);

        return ArticleDto.of(articleOptional.get());
    }

    @Override
    public List<CommentDto> getComments(Long articleId) {
        Optional<Article> articleOptional = articleRepository.findById(articleId);

        if(!articleOptional.isPresent())
            return Collections.emptyList();

        Article article = articleOptional.get();
        List<Comment> comments = article.getCommentList();

        Collections.sort(comments);
        return CommentDto.of(comments);
    }

    @Override
    public void registerComment(Long id, CommentDto commentDto, String name) {

        Comment comment = Comment.builder()
                .text(commentDto.getText())
                .userName(name)
                .regDt(LocalDateTime.now())
                .build();

        commentRepository.save(comment);

        Optional<Article> articleOptional = articleRepository.findById(id);

        if(!articleOptional.isPresent())
            return;

        Article article = articleOptional.get();
        List<Comment> comments = article.getCommentList();

        comments.add(comment);

        article.setCommentList(comments);
        articleRepository.save(article);
    }
}
