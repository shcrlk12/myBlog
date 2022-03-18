package com.kjwon.myblog.course.model;

import com.kjwon.myblog.admin.model.CommonParam;
import lombok.Data;

@Data
public class CourseParam extends CommonParam {

    long id;//course.id
    long categoryId;

}
