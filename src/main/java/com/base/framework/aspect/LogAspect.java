package com.base.framework.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.base.framework.BaseComponent.BaseAspect;

@Aspect
@Component
public class LogAspect extends BaseAspect{
	
	private ThreadLocal<Long> startTime = new ThreadLocal<Long>();

    @Pointcut("execution(* com.api..*.*(..))")  
    public void pointcut(){}
    
    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) {
    	initLog(joinPoint);
    	startTime.set(System.currentTimeMillis());
    	getLogger().info("方法:" + getName(joinPoint)+" 开始");
    }
    @AfterReturning(returning = "ret", pointcut = "pointcut()")  
    public void doAfterReturning(JoinPoint joinPoint){
    	getLogger().info("方法:" + getName(joinPoint)+" 返回");
    }
    
    @AfterThrowing(value = "pointcut()",throwing = "exception")
    public void doAfterThrowing(JoinPoint joinPoint,Throwable exception){
    	getLogger().error(getStackTrace(exception));
    }
    @After("pointcut()")
    public void doAfter(JoinPoint joinPoint){
    	getLogger().info("方法:" + getName(joinPoint)+" 结束");
    }
    
}
