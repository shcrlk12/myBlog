package com.kjwon.myblog.admin.model;


import lombok.Data;

@Data
public class CategoryInput {
    
    long id;
    String categoryName;
    String articlePath;

    int sortValue;
    boolean usingYn;
    
}
