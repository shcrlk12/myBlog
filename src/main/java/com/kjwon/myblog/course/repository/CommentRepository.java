package com.kjwon.myblog.course.repository;

import com.kjwon.myblog.course.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
