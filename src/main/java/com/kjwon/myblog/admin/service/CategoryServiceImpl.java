package com.kjwon.myblog.admin.service;

import com.kjwon.myblog.acrticle.Dto.ArticleDto;
import com.kjwon.myblog.admin.dto.CategoryDto;
import com.kjwon.myblog.admin.entity.Category;
import com.kjwon.myblog.admin.model.CategoryInput;
import com.kjwon.myblog.admin.repository.CategoryRepository;
import com.kjwon.myblog.course.controller.CourseController;
import com.kjwon.myblog.course.dto.CourseDto;
import com.kjwon.myblog.course.entity.Course;
import com.kjwon.myblog.course.repository.CourseRepository;
import com.kjwon.myblog.member.entity.Member;
import com.kjwon.myblog.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private  static final org.apache.log4j.Logger log = Logger.getLogger(CategoryServiceImpl.class);

    private final CategoryRepository categoryRepository;
    private final CourseRepository courseRepository;
    private final MemberRepository memberRepository;

    private Sort getSortBySortValueDesc() {
        return Sort.by(Sort.Direction.DESC, "sortValue");
    }
    
    @Override
    public List<CategoryDto> list() {

        return CategoryDto.of(categoryRepository.findAll());
    }
    
    @Override
    public boolean add(CategoryInput parameter) {
        
        //카테고리명이 중복인거 체크
        
        Category category = Category.builder()
                .categoryName(parameter.getCategoryName())
                .usingYn(true)
                .sortValue(0)
                .articlePath(parameter.getArticlePath())
                .build();

        categoryRepository.save(category);

        return true;
    }
    
    @Override
    public boolean update(CategoryInput parameter, String userName) {
        
        Optional<Category> optionalCategory = categoryRepository.findById(parameter.getId());
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setWriter(userName);
            category.setCategoryName(parameter.getCategoryName());
            category.setSortValue(parameter.getSortValue());
            category.setUsingYn(parameter.isUsingYn());
            category.setArticlePath(parameter.getArticlePath());
            categoryRepository.save(category);
        }
        
        return true;
    }
    
    @Override
    public boolean del(long id) {
        
        categoryRepository.deleteById(id);
        
        return true;
    }

    public List<Category> getCategory(String userName){
        Optional<Member> memberOptional = memberRepository.findById(userName);

        if(!memberOptional.isPresent())
            return Collections.emptyList();

        return memberOptional.get().getCategoryList();
    }

    @Override
    public List<CategoryDto> frontList(String userName) {

        List<CategoryDto> categoryDtoList = new ArrayList<>();
        List<Category> categories = getCategory(userName);

        for(int i =0; i < categories.size(); i++){
            Category category = categories.get(i);
            CategoryDto categoryDto = CategoryDto.of(category);

            categoryDto.setCourseCount(category.getCourseList().size());
            categoryDtoList.add(categoryDto);
        }

        return categoryDtoList;
    }

    @Override
    public List<CourseDto> findAllCourse() {
        List<Course> courseList = courseRepository.findAllBlogAndCategory();

        if (courseList.isEmpty())
            return Collections.emptyList();

        return CourseDto.of(courseList);
    }

    @Override
    public List<ArticleDto> frontList2(String categoryName) {


        return null;
    }
}
