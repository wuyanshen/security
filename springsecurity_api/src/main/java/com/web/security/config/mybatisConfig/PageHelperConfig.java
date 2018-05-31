package com.web.security.config.mybatisConfig;

import com.github.pagehelper.PageInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * 分页配置
 *
 * 以下配置非常的重要，PageHelper的Page拦截器PageInterceptor，如果不进行配置，那么分页功能将没有效果
 */
@Configuration
public class PageHelperConfig {
    @Value("${pagehelper.helper-dialect}")
    private String helperDialect;


    @Bean
    public PageInterceptor pageInterceptor(){
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("helperDialect", helperDialect);
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }
}
