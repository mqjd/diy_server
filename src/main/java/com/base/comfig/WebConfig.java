package com.base.comfig;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.base.comfig.filter.SessionFilter;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean<Filter> someFilterRegistration() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<Filter>();
        registration.setFilter(sessionFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("sessionFilter");
        return registration;
    }
    
    
    @Bean(name = "sessionFilter")
    public Filter sessionFilter() {
        return new SessionFilter();
    }
	
}
