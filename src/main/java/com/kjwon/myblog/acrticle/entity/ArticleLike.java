package com.kjwon.myblog.acrticle.entity;

import com.kjwon.myblog.member.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ArticleLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "member_userId")
    private Member member;

    private LocalDateTime choiceTime;

    @ManyToOne
    @JoinColumn(name="article_articleId")
    private Article article;
}
