
package com.unicom.security.config;
 

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;
  private final LogoutHandler logoutHandler;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	 
    http
        .csrf()
        
        .disable()
        .cors()
        .disable()
        .authorizeHttpRequests()
        //.requestMatchers(HttpMethod.PUT, "/api/v1/missions/**").hasRole("MANAGER")
        .requestMatchers("/api/v1/auth/**").permitAll()
        .requestMatchers("/api/v1/auth/missions/**").permitAll()
        .requestMatchers("/api/v1/auth/announcements/**").permitAll()
        .requestMatchers("/api/v1/auth/formateurEx/**").permitAll()
        .requestMatchers("/api/v1/auth/pointages/**").permitAll()
        .requestMatchers("/api/user-registration/submit").permitAll()
        .requestMatchers("/api/v1/auth/formations/**").permitAll()
        .requestMatchers("/api/v1/auth/messages/**").permitAll()
        .requestMatchers("/api/v1/auth/departments/**").permitAll()
        .requestMatchers("/api/v1/auth/leaverequests/**").permitAll()
        .requestMatchers("/api/v1/auth/comments/**").permitAll()
        .requestMatchers("/api/v1/auth/users/**").permitAll()
        
        .anyRequest()
          .authenticated()
        .and()
          .sessionManagement()
          .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .logout()
        .logoutUrl("/api/v1/auth/logout")
        .addLogoutHandler(logoutHandler)
        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
    ;

    return http.build();
    
  }
  
}