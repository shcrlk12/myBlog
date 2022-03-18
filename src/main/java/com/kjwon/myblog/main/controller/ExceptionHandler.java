package com.kjwon.myblog.main.controller;

import com.kjwon.myblog.admin.service.CategoryServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
    private  static final org.apache.log4j.Logger log = Logger.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public void Test(Exception e){
        log.error(e.getStackTrace());
    }
}
