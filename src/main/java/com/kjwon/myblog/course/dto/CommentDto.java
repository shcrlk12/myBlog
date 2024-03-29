package com.kjwon.myblog.course.dto;

import com.kjwon.myblog.course.entity.Comment;
import com.kjwon.myblog.course.entity.Course;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Setter
@Getter
public class CommentDto {
    Long id;
    String commentText;
    String userName;
    LocalDateTime regDt;

    public static CommentDto of(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .commentText(comment.getText())
                .userName(comment.getUserName())
                .regDt(comment.getRegDt())
                .build();
    }

    public static List<CommentDto> of(List<Comment> comments) {

        if (comments == null) {
            return null;
        }

        List<CommentDto> commentSet = new ArrayList<>();
        for(Comment x : comments) {
            commentSet.add(CommentDto.of(x));
        }
        return commentSet;

    }
}
