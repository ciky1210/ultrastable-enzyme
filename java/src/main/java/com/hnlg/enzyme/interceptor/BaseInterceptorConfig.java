package com.hnlg.enzyme.interceptor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 拦截器设置
 */
@Configuration
public class BaseInterceptorConfig extends WebMvcConfigurationSupport {

    @Autowired
    private TokenInterceptor tokenInterceptor;


    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/enzymeAdminUser/*")
                .excludePathPatterns(
                        "/"
                        , "/login/**");
        super.addInterceptors(registry);
    }

}
