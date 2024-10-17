package com.example.spring.todolist.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")  // 모든 경로에 대해 CORS 설정을 적용
                .allowedOrigins("http://127.0.0.1:3000")   // 특정 도메인의 리소스에만 접근하도록 허용
                .allowedMethods("GET","POST","PUT","DELETE")    // HTTP 메서드중 허용가능한 메서드 종류
                .allowedHeaders("Header1","Header2","Header3") // 클라이언트 사용시 사용가능한 헤더수
                .exposedHeaders("Header1","Header2") // 클라이언트 응답시 노출할 수 있는 헤더를 정의
                .allowCredentials(false)    // CORS 자격증명을 허용하지 않겠다는 행위
                .maxAge(3600);   // CORS 요청에 대해 캐시할 수 있는 최대 시간 정의
    }

}
