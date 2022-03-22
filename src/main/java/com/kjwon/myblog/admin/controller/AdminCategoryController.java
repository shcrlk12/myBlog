package com.kjwon.myblog.admin.controller;


import com.kjwon.myblog.admin.dto.CategoryDto;
import com.kjwon.myblog.admin.model.CategoryInput;
import com.kjwon.myblog.admin.model.MemberParam;
import com.kjwon.myblog.admin.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminCategoryController {
    
    private final CategoryService categoryService;
    
    @GetMapping("/admin/category/list.do")
    public String list(Model model, MemberParam parameter, Principal principal) {
        
        List<CategoryDto> list = categoryService.list();
        model.addAttribute("list", list);
      
        return "/admin/category/list";
    }
    
    
    @PostMapping("/admin/category/add.do")
    public String add(Model model, CategoryInput parameter) {
    
        boolean result = categoryService.add(parameter);
    
        return "redirect:/admin/category/list.do";
    }
    
    @PostMapping("/admin/category/delete.do")
    public String del(Model model, CategoryInput parameter) {
        
        boolean result = categoryService.del(parameter.getId());
        
        return "redirect:/admin/category/list.do";
    }
    
    @PostMapping("/admin/category/update.do")
    public String update(Model model, CategoryInput parameter, Principal principal) {
        
        boolean result = categoryService.update(parameter, principal.getName());
        
        return "redirect:/admin/category/list.do";
    }
    
}
