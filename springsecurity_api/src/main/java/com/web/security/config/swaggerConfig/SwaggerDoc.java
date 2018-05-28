package com.web.security.config.swaggerConfig;

import com.fasterxml.classmate.TypeResolver;
import com.web.security.entity.MyUser;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.OperationBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingScannerPlugin;
import springfox.documentation.spi.service.contexts.DocumentationContext;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

/**
 * swagger自定义文档接口
 * @author YanShen.Wu
 * @date 2018/5/28 10:05:21
 */
@Component
public class SwaggerDoc implements ApiListingScannerPlugin {

    @Override
    public List<ApiDescription> apply(DocumentationContext documentationContext) {
        return new ArrayList<ApiDescription>(
                Arrays.asList(
                        new ApiDescription(
                                "/login",  //url
                                "UserToken", //描述
                                Arrays.asList(
                                        new OperationBuilder(
                                                new CachingOperationNameGenerator())
                                                .method(HttpMethod.POST)//http请求类型
                                                .produces(newHashSet(MediaType.APPLICATION_JSON_VALUE))
                                                .summary("获取token")
                                                .notes("获取token")//方法描述
                                                .tags(newHashSet("用户登录获取Token"))//归类标签
                                                .parameters(
                                                        Arrays.asList(
                                                                new ParameterBuilder()
                                                                        .description("用户")
                                                                        .type(new TypeResolver().resolve(MyUser.class))
                                                                        .name("MyUser")
                                                                        .parameterType("body")
                                                                        .parameterAccess("access")
                                                                        .required(true)
                                                                        .modelRef(new ModelRef("MyUser")) //<5>
                                                                        .build()
                                                        )).responseMessages(responseMessages())
                                                .build()),
                                false)));
    }


    private Set<ResponseMessage> responseMessages(){
        return newHashSet(
                new ResponseMessageBuilder()
                        .code(200)
                        .message("Successful recived response")
                        .responseModel(new ModelRef("string"))//定义返回值类型
                        .build(),
                new ResponseMessageBuilder()
                        .code(401)
                        .message("Unauthorized")
                        .build(),
                new ResponseMessageBuilder()
                        .code(403)
                        .message("Forbidden")
                        .build(),
                new ResponseMessageBuilder()
                        .code(404)
                        .message("Not Found")
                        .build()
        );
    }

    @Override
    public boolean supports(DocumentationType documentationType) {
        return DocumentationType.SWAGGER_2.equals(documentationType);
    }
}
