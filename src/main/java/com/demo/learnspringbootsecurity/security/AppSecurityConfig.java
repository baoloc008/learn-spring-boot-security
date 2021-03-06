package com.demo.learnspringbootsecurity.security;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.demo.learnspringbootsecurity.security.UserPermission.*;
import static com.demo.learnspringbootsecurity.security.UserRole.*;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll() // Cho phép tất cả mọi người truy cập vào các địa chỉ này

                .antMatchers("/api/**").hasRole(STUDENT.name())

                .antMatchers("/management/api/**").hasAuthority(COURSE_WRITE.name())


                .anyRequest().authenticated() // Tất cả các request khác đều cần phải xác thực mới được truy cập
                .and()
                .formLogin() // Cho phép người dùng xác thực bằng form login
                .defaultSuccessUrl("/hello")
                .and()
                .logout() // Cho phép logout
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails baoloc008 = User.builder()
                .username("baoloc008")
                .password(passwordEncoder.encode("123456"))
                .roles(STUDENT.name()) // ROLE_STUDENT
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .roles(ADMIN.name())
                .build();

        UserDetails tom = User.builder()
                .username("tom")
                .password(passwordEncoder.encode("tom"))
                .roles(ADMINTRAINEE.name())
                .build();

        return new InMemoryUserDetailsManager(
                baoloc008,
                admin,
                tom
        );
    }
}
