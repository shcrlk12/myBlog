package com.kjwon.myblog.course.entity;

import com.kjwon.myblog.acrticle.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Comment implements Comparable<Comment>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String text;
    String userName;
    LocalDateTime regDt;

    @ManyToOne
    @JoinColumn(name ="article_articleId")
    private Article article;

    @Override
    public int compareTo(Comment o) {
        return Long.valueOf(getId() - o.getId()).intValue();
    }
}
