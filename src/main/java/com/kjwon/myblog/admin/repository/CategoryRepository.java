package com.kjwon.myblog.admin.repository;

import com.kjwon.myblog.admin.entity.Category;
import com.kjwon.myblog.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
