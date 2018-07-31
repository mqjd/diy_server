package com.base.framework.BaseComponent;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseAspect extends BaseComponent{

	ThreadLocal<Logger> loggers = new ThreadLocal<Logger>();
	
	protected String getSourceClass(JoinPoint joinPoint) {
		return joinPoint.getTarget().getClass().getName();
	}
	
	protected String getName(JoinPoint joinPoint) {
		return joinPoint.getSignature().getName();
	}
	
	protected void initLog(JoinPoint joinPoint){
		Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
		loggers.set(logger);
	}
	
	protected Logger getLogger() {
		return loggers.get();
	}
	
}
