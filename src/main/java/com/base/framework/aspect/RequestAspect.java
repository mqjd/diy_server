package com.base.framework.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.base.framework.BaseComponent.BaseAspect;

@Aspect
@Component
public class RequestAspect extends BaseAspect{
	
	private ThreadLocal<Long> startTime = new ThreadLocal<Long>();

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void pointcut(){}
    
    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) {
    	startTime.set(System.currentTimeMillis());
    	ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    	HttpServletRequest request = attributes.getRequest();
    	logger.info("请求开始:" + request.getRequestURI());
    }
    @AfterReturning(returning = "ret", pointcut = "pointcut()")  
    public void doAfter(JoinPoint joinPoint){
    	logger.info("请求耗时：" + (System.currentTimeMillis() - startTime.get())+"ms");
    }
    
    @AfterThrowing(value = "pointcut()",throwing = "exception")
    public void doAfterThrowing(JoinPoint joinPoint,Throwable throwable){
    	logger.info("请求异常：" + getStackTrace(throwable));
    }
	
}
