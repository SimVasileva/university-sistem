package com.university.universityproject.configuration;

import com.university.universityproject.model.UserRoleEnum;
import com.university.universityproject.repository.UserRepository;
import com.university.universityproject.service.impl.AppUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // http exposes api that allows us to configure the web security
        http.
                // which pages will be authorized?
                        authorizeHttpRequests()
                // allow CSS at "common" static location (static/css)
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                // permit home page, login and registration pages for anyone
                .requestMatchers("/", "/users/home").permitAll()
                // allow for Student
                .requestMatchers("/students/**").hasAnyRole("ADMIN", "STUDENT")
                // allow for Teachers
                .requestMatchers("/teachers/**").hasAnyRole("ADMIN", "TEACHER")
                // allow for Admins
                .requestMatchers("/teachers/**", "/students/**", "/users/**").hasRole(UserRoleEnum.ADMIN.name())
                // any remaining reqests should be authenticated
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/")
                .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                .defaultSuccessUrl("/home")
                .failureForwardUrl("/login-error")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()
                .csrf().disable();

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new AppUserDetailsService(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
