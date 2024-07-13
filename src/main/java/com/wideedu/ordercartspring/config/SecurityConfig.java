package com.wideedu.ordercartspring.config;

import com.wideedu.ordercartspring.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserServiceImpl userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry -> {
                    registry.requestMatchers("/api/register/**").permitAll();
//                    registry.requestMatchers("/api/orders/**").hasRole("ADMIN");
//                    registry.requestMatchers("/cart").hasRole("USER");
//                    registry.requestMatchers("/products").hasRole("USER");
                    registry.anyRequest().authenticated();
                })
                .formLogin(httpSecurityFormLoginConfigurer -> {
                    httpSecurityFormLoginConfigurer
                            .loginPage("/login")
                            .successHandler(new AuthenticationSuccessHandler())
                            .permitAll();
                })
                .logout(logoutConfigurer -> logoutConfigurer
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login") // You can change this to a different URL if needed
                        .permitAll()
                )
                .build();

    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails normalUser = User.builder()
//                .username("user")
//                .password("$2a$12$APt.GqpQBEjeIYsRMhSQlehPV9JwXW5CzmvgXbTcw0ixkXC8tzViS")
//                .roles("USER")
//                .build();
//        UserDetails adminUser = User.builder()
//                .username("admin")
//                .password("$2a$12$APt.GqpQBEjeIYsRMhSQlehPV9JwXW5CzmvgXbTcw0ixkXC8tzViS")
//                .roles("ADMIN", "USER")
//                .build();
//        return new InMemoryUserDetailsManager(normalUser, adminUser);
//    }

    @Bean
    public UserDetailsService userDetailsService() {
        return userService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
