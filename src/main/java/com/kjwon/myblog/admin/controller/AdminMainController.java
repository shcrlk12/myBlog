package com.kjwon.myblog.admin.controller;


import com.kjwon.myblog.main.controller.CustomExceptionHandler;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
public class AdminMainController {
    private  static final Logger log = Logger.getLogger(AdminMainController.class);


    @GetMapping("/admin/main.do")
    public String main() {
        
        return "admin/main";
    }

    @GetMapping("/admin/blog/default.do")
    public String blogDefaultImageChange(){
        return "admin/blog-default-img";
    }

    @PostMapping("/admin/blog/default.do")
    public String postBlogDefaultImageChange(MultipartFile file) throws IOException {

        log.debug("file name " + file.getOriginalFilename());

        File newFile = new File("/usr/local/tomcat/img/blog/thumbnail/default.jpg");

        if(newFile.exists())
            if(newFile.delete())
                log.info("default 파일 업로드 성공");
            else
                log.info("default 파일 업로드 실패");

//        FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(newFile));

        return "admin/blog-default-img";
    }

    
    
    
}
