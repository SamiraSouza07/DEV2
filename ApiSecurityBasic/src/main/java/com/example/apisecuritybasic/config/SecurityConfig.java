package com.example.apisecuritybasic.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        http.authorizeHttpRequests(authorize -> authorize
//                .requestMatchers("/login").permitAll()
//                .requestMatchers("/style.css").permitAll()
//                .requestMatchers("/home/**").hasRole("USER")
//                .requestMatchers("/admin/**").hasRole("ADMIN")
//                .requestMatchers(HttpMethod.POST, "/admin/**").hasRole("ADMIN")
//                .anyRequest().authenticated()
//        );
//
//    }
}
