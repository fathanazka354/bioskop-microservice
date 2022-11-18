package com.cinema.usermicroservice.security;//package com.binar.cinema.security;
//
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//
//@Configuration
//public class CrossOriginConfiguration {
//    @Bean
//    public FilterRegistrationBean corsFilter(){
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config =  new CorsConfiguration();
//        config.setAllowCredentials(Boolean.TRUE);
//        config.addAllowedMethod(CorsConfiguration.ALL);
//        config.addAllowedOrigin(CorsConfiguration.ALL);
//        config.addAllowedHeader(CorsConfiguration.ALL);
//        source.registerCorsConfiguration("/**",config);
//        FilterRegistrationBean bean = new FilterRegistrationBean<>(new CorsFilter(source));
//        bean.setOrder(0);
//        return bean;
//    }
//}
