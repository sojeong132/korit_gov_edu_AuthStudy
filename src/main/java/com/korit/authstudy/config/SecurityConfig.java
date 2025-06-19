package com.korit.authstudy.config;

import com.korit.authstudy.filter.StudyFilter;
import com.korit.authstudy.security.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final StudyFilter studyFilter;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 요청을 보내는 쪽의 도메인(사이트 주소)
        corsConfiguration.addAllowedOriginPattern(CorsConfiguration.ALL);
        // 요청을 보내는 쪽에서 Request, Response HEADER 정보에 대한 제약
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
        // 요청을 보내는 쪽의 메소드(GET, POST, PUT, DELETE, OPTION 등)
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);

        // 요청 URL(/ali/users )에 대한 CORS 설정 적용을 위해 객체생성
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(Customizer.withDefaults());       // 위에서 만든 cors 설정(bean) security에 적용
        http.csrf(csrf -> csrf.disable());          // 서버사이드 렌더링 방식이 아니니 REST API 방식에서는 비활성화
        http.formLogin(formLogin -> formLogin.disable());   // 서버사이드 렌더링 로그인 방식 비활성화
        http.httpBasic(httpBasic -> httpBasic.disable());   // HTTP 프로토콜 기본 로그인 방식 비활성화
        http.logout(logout -> logout.disable());            // 서버사이드 렌더링 로그아웃 방식 비활성화

//        http.addFilterBefore(studyFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // 특정 요청 URL에 대한 권한 설정
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/api/users/login", "/api/users", "/api/users/login/status").permitAll();
            auth.anyRequest().authenticated();
        });

        // HttpSecurity 객체에 설정한 모든 정보를 기반으로 build하여 SecurityFilterChain 객체를 생성한 후 Bean 등록

        return http.build();
    }
}
