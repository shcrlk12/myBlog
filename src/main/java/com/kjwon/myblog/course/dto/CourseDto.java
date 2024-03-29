package com.kjwon.myblog.course.dto;

import com.kjwon.myblog.admin.entity.Category;
import com.kjwon.myblog.course.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CourseDto {
    
    Long id;
    long categoryeeId;
    String imagePath;
    String keyword;
    String subject;
    String summary;
    String contents;
    LocalDateTime regDt;//등록일(추가날짜)
    LocalDateTime udtDt;//수정일(수정날짜)
    
    String filename;
    String urlFilename;

    String categoryName;
    //추가컬럼
    long totalCount;
    long seq;

    Category category;
    public static CourseDto of(Course course) {
    return CourseDto.builder()
            .id(course.getId())
            .categoryeeId(course.getCategoryeeId())
            .imagePath(course.getImagePath())
            .keyword(course.getKeyword())
            .subject(course.getSubject())
            .summary(course.getSummary())
            .contents(course.getContents())
            .regDt(course.getRegDt())
            .udtDt(course.getUdtDt())
            .filename(course.getFilename())
            .urlFilename(course.getUrlFilename())
            .category(course.getCategory())
            .build();
    }
    
    public static List<CourseDto> of(List<Course> courses) {
        
        if (courses == null) {
            return null;
        }
    
        List<CourseDto> courseList = new ArrayList<>();
        for(Course x : courses) {
            courseList.add(CourseDto.of(x));
        }
        return courseList;
    }
    
}

















