package com.kjwon.myblog.acrticle.service;

import com.kjwon.myblog.acrticle.Dto.ArticleDto;
import com.kjwon.myblog.acrticle.Dto.LikeUnlikeDto;
import com.kjwon.myblog.acrticle.entity.Article;
import com.kjwon.myblog.acrticle.entity.ArticleLike;
import com.kjwon.myblog.acrticle.entity.ArticleUnlike;
import com.kjwon.myblog.acrticle.repository.ArticleLikeRepository;
import com.kjwon.myblog.acrticle.repository.ArticleRepository;
import com.kjwon.myblog.acrticle.repository.ArticleUnlikeRepository;
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
import org.apache.tomcat.jni.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
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
    private final ArticleLikeRepository articleLikeRepository;
    private final ArticleUnlikeRepository articleUnlikeRepository;

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
    public Page<ArticleDto> frontList(int page, String articleType) {

        Optional<Category> articleOptional = categoryRepository.findByArticlePath(articleType);
        Page<Article> articleList = null;

        String categoryName = articleOptional.get().getCategoryName();

        if(categoryName.equals("인기 게시판")){
            articleList = articleRepository.findByAllIsPopularArticle(PageRequest.of(page - 1,30));
        }
        else
        {
            articleList = articleRepository.findByArticleOnCategory(categoryName, PageRequest.of(page - 1,30));
        }

        return articleList.map(ArticleDto::of);
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

        System.out.println(article);
        System.out.println(comments.size());

        return CommentDto.of(comments);
    }

    @Override
    public void registerComment(Long id, CommentDto commentDto, String name) {

        Comment comment = Comment.builder()
                .text(commentDto.getCommentText())
                .userName(name)
                .regDt(LocalDateTime.now())
                .article(articleRepository.findById(id).get())
                .build();

        commentRepository.save(comment);

        Optional<Article> articleOptional = articleRepository.findById(id);

        if(!articleOptional.isPresent())
            return;

        Article article = articleOptional.get();
        List<Comment> comments = article.getCommentList();

        comments.add(comment);

        article.setCommentList(comments);

        System.out.println(article.getArticleId());
        articleRepository.save(article);
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public void articleLike(Long id, String likeUser) {

        List<ArticleLike> articleLikeList = articleLikeRepository.findByArticleAndMember(new Article(id), new Member(likeUser));

        if(!articleLikeList.isEmpty())
            return;

        ArticleLike articleLike = ArticleLike.builder()
                .member(memberRepository.getById(likeUser))
                .choiceTime(LocalDateTime.now())
                .article(articleRepository.getById(id))
                .build();

        articleLikeRepository.save(articleLike);
    }

    @Override
    public LikeUnlikeDto getLikeUnLike(Long id) {

        Article article = articleRepository.getById(id);
        LikeUnlikeDto result = new LikeUnlikeDto();

        result.setLike(article.getArticleLikeList().size());
        result.setUnlike(article.getArticleUnlikeList().size());

        return result;
    }

    @Override
    public void articleUnlike(Long id, String likeUser) {

        List<ArticleUnlike> articleUnlikeList = articleUnlikeRepository.findByArticleAndMember(new Article(id), new Member(likeUser));

        if(!articleUnlikeList.isEmpty())
            return;

        ArticleUnlike articleUnlike = ArticleUnlike.builder()
                .member(memberRepository.getById(likeUser))
                .choiceTime(LocalDateTime.now())
                .article(articleRepository.getById(id))
                .build();

        articleUnlikeRepository.save(articleUnlike);
    }

    @Override
    public void postPopularArticle(Long id) {
        Article article = articleRepository.getById(id);

        article.setManyChoiceTime(LocalDateTime.now());
        article.setPopularArticle(true);

        articleRepository.save(article);
    }
}
