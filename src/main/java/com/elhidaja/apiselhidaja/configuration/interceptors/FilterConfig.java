package com.elhidaja.apiselhidaja.configuration.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Autowired
    private RequestBodyCachingFilter requestBodyCachingFilter;

    @Bean
    public FilterRegistrationBean<RequestBodyCachingFilter> registerRequestBodyCachingFilter() {
        FilterRegistrationBean<RequestBodyCachingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(requestBodyCachingFilter);
        registrationBean.addUrlPatterns("/api/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}