package com.kjwon.myblog.admin.dto;

import com.kjwon.myblog.admin.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDto {
    
    Long id;
    String categoryName;
    String articlePath;
    int sortValue;
    boolean usingYn;
    
    
    //ADD COLUMNS
    int courseCount;
    
    
    public static List<CategoryDto> of (List<Category> categories) {
        if (categories != null) {
            List<CategoryDto> categoryList = new ArrayList<>();
            for(Category x : categories) {
                categoryList.add(of(x));
            }
            return categoryList;
        }
        
        return null;
    }
    
    public static CategoryDto of(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .sortValue(category.getSortValue())
                .usingYn(category.isUsingYn())
                .articlePath(category.getArticlePath())
                .build();
    }
    
    
}
