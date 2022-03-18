package com.kjwon.myblog.admin.service;

import com.kjwon.myblog.admin.dto.CategoryDto;
import com.kjwon.myblog.admin.entity.Category;
import com.kjwon.myblog.admin.model.CategoryInput;
import com.kjwon.myblog.course.dto.CourseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryService {
    
    List<CategoryDto> list(String name);
    
    /**
     * 카테고리 신규 추가
     */
    boolean add(String categoryName, String userName);
    
    /**
     * 카테고리 수정
     */
    boolean update(CategoryInput parameter, String userName);
    
    /**
     * 카테고리 삭제
     */
    boolean del(long id);
    
    
    /**
     * 프론트 카테고리 정보
     */
    List<CategoryDto> frontList(String userName);
    

    List<CourseDto> findAllCourse();

}
