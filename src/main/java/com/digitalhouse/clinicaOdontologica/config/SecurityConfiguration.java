package com.digitalhouse.clinicaOdontologica.config;

import com.digitalhouse.clinicaOdontologica.config.jwt.JwtAuthenticationEntryPoint;
import com.digitalhouse.clinicaOdontologica.config.jwt.JwtRequestFilter;
import com.digitalhouse.clinicaOdontologica.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder noOpPasswordEncoder)
            throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(noOpPasswordEncoder);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .requestMatchers(HttpMethod.GET, "/odontologos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/odontologos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/odontologos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/odontologos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/turnos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/turnos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/turnos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/turnos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/pacientes/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/pacientes/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/pacientes/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/pacientes/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/odontologos/**").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/turnos/**").hasRole("ODONTOLOGO")
                        .requestMatchers("/pacientes/**").hasRole("ADMIN")
                        .requestMatchers("/checkAuthenticated/**").authenticated()
                        .requestMatchers("/authenticate/**", "/swagger-ui/**", "/v3/api-docs",
                                "/auth/v1/login",
                                "/view**", "/view/**",
                                "/v3/api-docs/swagger-config",
                                "/view/login.html"
                        )
                        .permitAll()

                        .requestMatchers(HttpMethod.POST, "/authenticate**").permitAll())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .userDetailsService(userService)
                .build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;
    }

    @Bean
    public String users() {

        UserDetails user = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        userService.createUser(user);

        UserDetails user2 = User.builder()
                .username("user")
                .password(passwordEncoder().encode("123456"))
                .roles("USER")
                .build();

        userService.createUser(user2);

        UserDetails user3 = User.builder()
                .username("odontologo")
                .password(passwordEncoder().encode("123456"))
                .roles("ODONTOLOGO")
                .build();

        return "";
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
