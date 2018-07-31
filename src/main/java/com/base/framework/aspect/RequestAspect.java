package com.base.framework.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.base.framework.BaseComponent.BaseAspect;

@Aspect
@Component
public class RequestAspect extends BaseAspect{
	
	private ThreadLocal<Long> startTime = new ThreadLocal<Long>();

    @Pointcut("execution(public * com.api..controller.*.*(..))")  
    public void pointcut(){}
    
    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) {
//    	startTime.set(System.currentTimeMillis());
//    	ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//    	HttpServletRequest request = attributes.getRequest();
//    	logger.info("请求开始:" + request.getRequestURI());
    }
    @AfterReturning(returning = "ret", pointcut = "pointcut()")  
    public void doAfter(JoinPoint joinPoint){
//    	logger.info("请求耗时：" + (System.currentTimeMillis() - startTime.get())+"ms");
    }
    
    @AfterThrowing("pointcut()")
    public void doAfterThrowing(JoinPoint joinPoint){
//    	logger.info("请求异常：");
    }
	
}
