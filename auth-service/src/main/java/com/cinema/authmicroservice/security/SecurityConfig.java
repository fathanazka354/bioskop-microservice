package com.cinema.authmicroservice.security;

import com.binar.cinema.security.filter.AuthenticationFilter;
import com.binar.cinema.security.filter.ExceptionHandlerFilter;
import com.binar.cinema.security.filter.JWTAuthorizationFilter;
import com.binar.cinema.security.manager.CustomAuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static com.binar.cinema.security.SecurityConstant.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private CustomAuthenticationManager customAuthenticationManager;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(customAuthenticationManager);
        authenticationFilter.setFilterProcessesUrl("/authenticate");
//        Way 1

//        http
//                .csrf().disable()
//                .authorizeRequests()
////                .anyRequest().authenticated()
//                .antMatchers("/swagger-ui/**").permitAll()
////                .antMatchers("/api-docs.html").permitAll()
//                .antMatchers(HttpMethod.POST, REGISTER_PATH).permitAll()
//                .antMatchers(HttpMethod.DELETE).authenticated()
//                .antMatchers(PAYMENT_PATH).permitAll()
//                .antMatchers(HttpMethod.GET, MOVIE_PATH).authenticated()
////                .antMatchers(HttpMethod.GET, ).authenticated()
//                .and()
//                .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)
//                .addFilter(authenticationFilter)
//                .addFilterAfter(new JWTAuthorizationFilter(), AuthenticationFilter.class)
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

//        Way 2
        http.csrf().disable()
                .antMatcher("/swagger-ui/**").authorizeRequests()
                .antMatchers(EMPLOYEE_PATH).authenticated()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers(CUSTOMER_PATH, MOVIE_PATH).hasRole("ADMIN").and()
                .userDetailsService(users())
                .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)
                .addFilter(authenticationFilter)
                .addFilterAfter(new JWTAuthorizationFilter(), AuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().httpBasic();
        return http.build();
    }

    private CorsConfigurationSource configurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.setAllowCredentials(true);
        config.addAllowedHeader("X-Requested-With");
        config.addAllowedHeader("Content-Type");
        config.addAllowedMethod(HttpMethod.POST);
        config.addAllowedMethod(HttpMethod.GET);
        config.addAllowedMethod(HttpMethod.PUT);
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public UserDetailsService users(){
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin-pass"))
                .roles("ADMIN")
                .build();
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("user-pass"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(admin, user);
    }
}
