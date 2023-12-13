package com.bitc.java501team3.configuration;

import com.bitc.java501team3.interceptor.LoginCheck;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVCConfiguration implements WebMvcConfigurer {

    @Value("${file.upload-dir}") // application.properties 또는 application.yml에서 설정된 경로를 주입
    private String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/img/**").addResourceLocations("file:///" + uploadPath);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
//        addInterceptors(): 사용자가 생성한 인터셉터 클래스 객체를 등록
        registry.addInterceptor(new LoginCheck())
//                addPathPatterns(): 인터셉터를 동작시킬 주소 패턴 등록
                .addPathPatterns("/board/*")
//               excludePathPatterns(): 인터셉터에서 제외할 주소 패턴 등록
                .excludePathPatterns("/board/boardList.do")
                .excludePathPatterns("/board/boardDetail.do")
                .addPathPatterns("/user/*")
                .excludePathPatterns("/user/join")
                .excludePathPatterns("/user/insert")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/loginProcess");
    }
}
