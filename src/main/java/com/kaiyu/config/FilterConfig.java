package com.kaiyu.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.kaiyu.filter.CorsFilter;
// import com.kaiyu.filter.RepeatableFilter;



@Configuration
public class FilterConfig {

    // @Bean
    // public FilterRegistrationBean<RepeatableFilter> someFilterRegistration() {
    //     FilterRegistrationBean<RepeatableFilter> registration = new FilterRegistrationBean<>();
    //     registration.setFilter(new RepeatableFilter());
    //     registration.addUrlPatterns("/*");
    //     registration.setName("repeatableFilter");
    //     registration.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE);
    //     return registration;
    // }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CorsFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }

}
