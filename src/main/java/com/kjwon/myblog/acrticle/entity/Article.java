package com.kjwon.myblog.acrticle.entity;

import com.kjwon.myblog.admin.entity.Category;
import com.kjwon.myblog.course.entity.Course;
import com.kjwon.myblog.member.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Article {

//    @EmbeddedId
//    private ArticleId articleId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "article_id")
    private Long articleId;

    private String title;
    @Lob
    private String contents;

    private boolean isDelete;

    private LocalDateTime manyChoiceTime;
    private LocalDateTime regDt;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "article")
    private List<ArticleLike> articleLikeList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="member_userId")
    private Member member;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

}
