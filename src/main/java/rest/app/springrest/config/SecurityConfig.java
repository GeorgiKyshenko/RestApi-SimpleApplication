package rest.app.springrest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth ->
                auth.anyRequest().authenticated());
//        http.httpBasic(Customizer.withDefaults());
        http.formLogin(Customizer.withDefaults()); // spring security provides URLs - /login and /logout
        http.csrf().disable();
        http.headers().frameOptions().disable();
        return http.build();
    }
}
