package com.bloodbank.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    // JDBC Authentication - Load users directly from database using SQL
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // Query to fetch username, password, enabled status from users table
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select username, password, true as enabled from users where username=?"
        );

        // Query to fetch role from users table and add ROLE_ prefix
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select username, concat('ROLE_', role) from users where username=?"
        );

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth

                // Public
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/donors/register").permitAll()
                .requestMatchers("/api/hospitals/register").permitAll()

                // ADMIN
                .requestMatchers("/api/admin/**").hasRole("ADMIN")

                // INVENTORY
                .requestMatchers("/api/inventory/**").hasAnyRole("ADMIN", "STAFF")

                // DONOR
                .requestMatchers("/api/donors/**").hasAnyRole("ADMIN", "STAFF", "DONOR")

                // HOSPITAL
                .requestMatchers("/api/hospitals/**").hasAnyRole("ADMIN", "HOSPITAL", "STAFF")

                // BLOOD REQUESTS
                .requestMatchers("/api/requests/**").hasAnyRole("ADMIN", "STAFF", "HOSPITAL")

                // PATIENTS
                .requestMatchers("/api/patients/**").hasAnyRole("ADMIN", "STAFF", "HOSPITAL")

                .anyRequest().authenticated()
        );

        http.httpBasic(Customizer.withDefaults());

        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}