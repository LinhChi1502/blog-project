package com.chinguyen.blogdemo.log;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class Logger {
    @AfterThrowing(pointcut = "execution(public * com.chinguyen.blogdemo.service.blog.BlogService.findAll(..))")
    public void error() {
        System.out.println("[CMS] ERROR!");
    }
}
