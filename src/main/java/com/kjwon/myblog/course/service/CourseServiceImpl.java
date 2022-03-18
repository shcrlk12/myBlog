package com.kjwon.myblog.course.service;

import com.kjwon.myblog.admin.entity.Category;
import com.kjwon.myblog.admin.repository.CategoryRepository;
import com.kjwon.myblog.course.dto.CommentDto;
import com.kjwon.myblog.course.dto.CourseDto;
import com.kjwon.myblog.course.entity.Comment;
import com.kjwon.myblog.course.entity.Course;
import com.kjwon.myblog.course.model.CourseInput;
import com.kjwon.myblog.course.model.CourseParam;
import com.kjwon.myblog.course.repository.CommentRepository;
import com.kjwon.myblog.course.repository.CourseRepository;
import com.kjwon.myblog.member.entity.Member;
import com.kjwon.myblog.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {
    
    private final CourseRepository courseRepository;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;

    private LocalDate getLocalDate(String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(value, formatter);
        } catch (Exception e) {
        
        }
        
        return null;
    }
    
    @Override
    public boolean add(CourseInput parameter, String userName) {

        Optional<Member> memberOptional = memberRepository.findById(userName);

        if(!memberOptional.isPresent())
            return false;

        Member member = memberOptional.get();
        List<Category> categories = member.getCategoryList();

        for(Category category : categories) {
            if (category.getId() == parameter.getCategoryId()) {
                List<Course> courseList = category.getCourseList();

                Course course = Course.builder()
                        .writer(userName)
                        .categoryeeId(parameter.getCategoryId())
                        .subject(parameter.getSubject())
                        .keyword(parameter.getKeyword())
                        .summary(parameter.getSummary())
                        .contents(parameter.getContents())
                        .regDt(LocalDateTime.now())
                        .filename(parameter.getFilename())
                        .urlFilename(parameter.getUrlFilename())
                        .build();

                courseRepository.save(course);

                courseList.add(course);

                categoryRepository.save(category);
            }
        }
        return true;
    }
    
    @Override
    public boolean set(CourseInput parameter, String userName) {
        
        Optional<Course> optionalCourse = courseRepository.findById(parameter.getId());
        if (!optionalCourse.isPresent()) {
            //수정할 데이터가 없음
            return false;
        }

        Course course = optionalCourse.get();
        course.setWriter(userName);
        course.setCategoryeeId(parameter.getCategoryId());
        course.setSubject(parameter.getSubject());
        course.setKeyword(parameter.getKeyword());
        course.setSummary(parameter.getSummary());
        course.setContents(parameter.getContents());
        course.setUdtDt(LocalDateTime.now());
        course.setFilename(parameter.getFilename());
        course.setUrlFilename(parameter.getUrlFilename());

        courseRepository.save(course);

        return true;
    }

    @Override
    public List<CourseDto> list(CourseParam parameter, String userName) {
        List<CourseDto> courseDtos = new ArrayList<>();

        List<Course> courseList = courseRepository.findByWriter(userName);

        if(courseList.isEmpty())
            return Collections.emptyList();

        for(Course course : courseList){
            CourseDto courseDto = CourseDto.of(course);
            courseDto.setTotalCount(courseList.size());
            courseDtos.add(courseDto);
        }

        return courseDtos;
    }
    
    @Override
    public CourseDto getById(long id) {
        return courseRepository.findById(id).map(CourseDto::of).orElse(null);
    }
    
    @Override
    public boolean del(String idList) {

        if (idList != null && idList.length() > 0) {
            String[] ids = idList.split(",");
            for (String x : ids) {
                long id = 0L;
                try {
                    id = Long.parseLong(x);
                } catch (Exception e) {
                }
                
                if (id > 0) {
                    courseRepository.deleteById(id);
                }
            }
        }
        
        return true;
    }

    public List<Category> getCategory(String userName){
        Optional<Member> memberOptional = memberRepository.findById(userName);

        if(!memberOptional.isPresent())
            return Collections.emptyList();

        return memberOptional.get().getCategoryList();
    }

    @Override
    public List<CourseDto> frontList(CourseParam parameter, String userName) {

//

//
        if(parameter.getCategoryId() < 1) {

            List<Course> courseList = courseRepository.findUserBlogAndCategory(userName);

            return CourseDto.of(courseList);
        }

        List<Category> categories = getCategory(userName);
        List<Course> courseList = new ArrayList<>();

        for (Category category : categories) {
            if(category.getId() == parameter.getCategoryId()) {
                List<Course> coursies = category.getCourseList();

                courseList.addAll(coursies);
                break;
            }
        }
        return CourseDto.of(courseList);

    }
    
    @Override
    public CourseDto frontDetail(long id) {
        
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isPresent()) {
            return CourseDto.of(optionalCourse.get());
        }
        return null;
    }
    
    @Override
    public List<CourseDto> listAll() {
        
        List<Course> courseList = courseRepository.findAll();
        
        return CourseDto.of(courseList);
    }

    @Override
    public void postComment(Long blogId, String text, String name) {

        Comment comment = Comment.builder()
                .text(text)
                .userName(name)
                .regDt(LocalDateTime.now())
                .build();

        commentRepository.save(comment);

        Optional<Course> courseOptional = courseRepository.findById(blogId);

        if(!courseOptional.isPresent())
            return;

        Course course = courseOptional.get();
        List<Comment> comments = course.getComments();

        comments.add(comment);

        course.setComments(comments);
        courseRepository.save(course);
        return;
    }

    @Override
    public List<CommentDto> getComments(long id) {

        Optional<Course> courseOptional = courseRepository.findById(id);

        if(!courseOptional.isPresent())
            return Collections.emptyList();

        Course course = courseOptional.get();
        List<Comment> comments = course.getComments();

        Collections.sort(comments);
        return CommentDto.of(comments);
    }
}


























