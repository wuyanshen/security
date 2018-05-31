package com.web.security.config.springSecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 * Spring Security配置类
 * @author YanShen.Wu
 * @date 2018/5/22 10:26:08
 */
@Configuration
@EnableWebSecurity //开启spring security过滤器链
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserDetailServiceImpl userDetailsService;

    // 设置 HTTP 验证规则
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 关闭csrf验证
        http.csrf().disable();

        //表单登录
        http.formLogin()
            .loginPage("/login").permitAll()
            .and()
            .logout().permitAll();

        // 对请求进行认证
        http.authorizeRequests()
                //匹配路径要从根路径写起
                //.antMatchers("/user/update").hasAnyAuthority("/user/update")
                // 所有 /login 的POST请求 都放行
                // 所有请求需要身份认证，并根据权限访问
                .anyRequest().access("@authService.canAccess(request,authentication)");

       /* // 添加一个过滤器 所有访问 /login 的请求交给 JWTLoginFilter 来处理 这个类处理所有的JWT相关内容
        http.addFilterAt(new JWTLoginFilter("/login", authenticationManager()),UsernamePasswordAuthenticationFilter.class)
            // 添加一个过滤器验证其他请求的Token是否合法
            .addFilterBefore(new JWTAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class);*/

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //spring5 这么做不起作用
        /*auth.inMemoryAuthentication()
                .withUser("user")
                .password("123")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("123")
                .authorities("WRITE_PRIVILEGES", "REDIS")
                .roles("MANAGER");*/

        /*auth.inMemoryAuthentication()
            .withUser(
                User.withDefaultPasswordEncoder()
                    .username("user")
                    .password("user")
                    .roles("USER")
                    .authorities("GET_INFO")
                    .build()
            ).withUser(
                User.withDefaultPasswordEncoder()
                        .username("manager")
                        .password("password")
                        .roles("MANAGER")
                        .authorities("REDIS,GET_INFO,FIND_INFO,UPDATE_INFO")
                        .build()
            );*/
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());

    }

    /**
     * 页面端权限配置
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        // Swagger2 权限放行
        web.ignoring().antMatchers("/swagger-ui.html","/resources/**","/webjars/**","/swagger-resources/**","/v2/**");
    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

}
