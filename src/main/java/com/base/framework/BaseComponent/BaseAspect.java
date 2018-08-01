package com.base.framework.BaseComponent;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseAspect extends BaseComponent{
	
	protected String getSourceClass(JoinPoint joinPoint) {
		return joinPoint.getTarget().getClass().getName();
	}
	
	protected String getName(JoinPoint joinPoint) {
		return joinPoint.getSignature().toString();
	}
	
	protected Logger getLogger(JoinPoint joinPoint) {
		Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
		return logger;
	}
	
}
