package com.kjwon.myblog.course.model;

import lombok.Data;

@Data
public class CourseInput {

    long id;
    long categoryId;
    String subject;
    String keyword;
    String summary;
    String contents;

    //삭제를 위한
    String idList;
    
    
    //ADD
    String filename;
    String urlFilename;

}
