package org.example.a25_07_esgi_rennes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SpringSecurityConfig {

    //version MVC
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // @formatter:off
        http.authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/testPrivate")  .authenticated()
                        .requestMatchers("/testPrivateAdmin").hasRole("ADMIN")
                        .anyRequest().permitAll()
                )
                .httpBasic(withDefaults())
                .formLogin(withDefaults());
        // @formatter:on
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        //Créer des utilisateurs fixes
        auth.inMemoryAuthentication()
                .passwordEncoder(encoder)
                .withUser("aaa")
                .password(encoder.encode("bbb"))
                .roles("USER")
                .and()
                .withUser("Admin")
                .password(encoder.encode("Admin"))
                .roles("ADMIN");
    }

}
