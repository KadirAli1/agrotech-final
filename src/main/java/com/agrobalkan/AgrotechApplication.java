package com.agrobalkan;

import com.agrobalkan.config.layout.ThymeleafLayoutInterceptorConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@SpringBootApplication
public class AgrotechApplication {

    @Configuration
    @Import({ThymeleafLayoutInterceptorConfig.class})
    public static class MainConfiguration extends WebMvcConfigurationSupport {


    }

    @Bean(name = "passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(16);
    }

    public static void main(String[] args) {SpringApplication.run(AgrotechApplication.class, args);}
}
