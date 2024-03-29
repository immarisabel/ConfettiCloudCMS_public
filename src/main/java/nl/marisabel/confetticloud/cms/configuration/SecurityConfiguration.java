package nl.marisabel.confetticloud.cms.configuration;

import lombok.RequiredArgsConstructor;
import nl.marisabel.confetticloud.cms.security.jwt.JwtAuthenticationEntryPoint;
import nl.marisabel.confetticloud.cms.security.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

 private final JwtAuthenticationFilter jwtAuthenticationFilter;

 private final JwtAuthenticationEntryPoint unauthorizedHandler;

 @Bean
 public AuthenticationManager authenticationManager(final AuthenticationConfiguration authenticationConfiguration) throws Exception {
  return authenticationConfiguration.getAuthenticationManager();
 }

 @Bean
 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
  return http.cors()
          .and().csrf().disable()
          .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
          .authorizeHttpRequests()
          .requestMatchers("/secret-passage/**","/health","/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/actuator/**").permitAll()
          .anyRequest().authenticated()
          .and()
          .exceptionHandling()
          .authenticationEntryPoint(unauthorizedHandler)
          .and()
          .sessionManagement()
          .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
          .and()
          .build();
 }




}
