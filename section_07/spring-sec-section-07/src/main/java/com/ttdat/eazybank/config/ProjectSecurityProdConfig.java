package com.ttdat.eazybank.config;

import com.ttdat.eazybank.exception.CustomAccessDeniedHandler;
import com.ttdat.eazybank.exception.CustomBasicAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("prod")
public class ProjectSecurityProdConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(sessionManagement ->
                        sessionManagement.invalidSessionUrl("/invalid-session")
                                .maximumSessions(1).maxSessionsPreventsLogin(true))
                .requiresChannel(channel -> channel.anyRequest().requiresSecure())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/accounts", "/balance", "/loans", "/cards").authenticated()
                        .requestMatchers("/contact", "/notices", "/register", "/invalid-session").permitAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(httpBasic ->
                        httpBasic.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()))
                .exceptionHandling(ehc -> ehc.accessDeniedHandler(new CustomAccessDeniedHandler()));;
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
