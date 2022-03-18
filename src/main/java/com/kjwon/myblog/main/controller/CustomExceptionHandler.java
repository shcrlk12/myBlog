package com.kjwon.myblog.main.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
    private  static final Logger log = Logger.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public void Test(Exception e){
        log.error(e.getMessage(), e);
        log.error(e.getLocalizedMessage(),e);

    }
}
