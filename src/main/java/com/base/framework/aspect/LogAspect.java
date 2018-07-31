package com.base.framework.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.base.framework.BaseComponent.BaseComponent;

@Aspect
@Component
public class LogAspect extends BaseComponent{
	
	private ThreadLocal<Long> startTime = new ThreadLocal<Long>();

    @Pointcut("execution(* com.api..*.*(..))")  
    public void pointcut(){}
    
    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) {
    	startTime.set(System.currentTimeMillis());
    	logger.info("方法开始");
    }
    @AfterReturning(returning = "ret", pointcut = "pointcut()")  
    public void doAfterReturning(JoinPoint joinPoint){
    	logger.info("方法返回，耗时："+ (System.currentTimeMillis() - startTime.get())+"ms");
    }
    
    @AfterThrowing("pointcut()")
    public void doAfterThrowing(JoinPoint joinPoint){
    	logger.info("方法异常");
    }
    @After("pointcut()")
    public void doAfter(JoinPoint joinPoint){
    	logger.info("方法结束");
    }
    
}
