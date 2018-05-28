package com.web.security.config.swaggerConfig;

import com.fasterxml.classmate.TypeResolver;
import com.web.security.entity.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;


/**
 * Swagger2 配置类
 * 访问地址 http://localhost:8089/swagger-ui.html
 * @author YanShen.Wu
 * @date 2018/5/24 16:45:25
 */
@Configuration
@EnableSwagger2//启用Swagger2
public class SwaggerConfig {


    @Autowired
    private TypeResolver typeResolver;

    @Bean
    public Docket createRestAPI(){

        //全局的接口文档参数中增加token输入项
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("Authorization").description("JWT令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())//来创建该Api的基本信息
                .select()//select()函数返回一个ApiSelectorBuilder实例用来控制哪些接口暴露给Swagger来展现
                //Swagger会扫描该包下所有Controller定义的API，并产生文档内容
                .apis(RequestHandlerSelectors.basePackage("com.web.security.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars)
                //添加实体模型
                .additionalModels(typeResolver.resolve(MyUser.class))
                ;
    }

    @Bean
    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("SpringSecurity API文档")//标题
                .contact("http://www.elisoft.com.cn")//联系方式
                .description("SpringSecurity 接口api")//描述
                .licenseUrl("http://www.elisoft.com.cn")//许可地址
                .termsOfServiceUrl("http://localhost:8000/")//服务地址
                .license("apache")//许可证
                .version("1.0")//版本
                .build();
    }


}
