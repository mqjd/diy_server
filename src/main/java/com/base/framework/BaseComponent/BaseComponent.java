package com.base.framework.BaseComponent;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseComponent {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected String getStackTrace(Throwable throwable) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		throwable.printStackTrace(pw);
		try{
			return sw.toString();
		} finally {
			pw.close();
		}
	}

}
