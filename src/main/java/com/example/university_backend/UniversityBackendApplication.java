package com.example.university_backend;

import com.example.university_backend.entity.Staff;
import com.example.university_backend.entity.Student;
import com.example.university_backend.repository.StaffRepository;
import com.example.university_backend.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.text.DateFormat;
import java.util.Collections;
import java.util.Date;

@SpringBootApplication
public class UniversityBackendApplication {

    private static final Logger log = LoggerFactory.getLogger("myApp");
    public static void main(String[] args) {
        SpringApplication.run(UniversityBackendApplication.class, args);
    }



    @Bean
    CommandLineRunner initDataBase(StudentRepository studentRepository, StaffRepository staffRepository) {
        return args -> {
            log.info("fill DB: " + studentRepository.save(new Student(9000L, "Stanley", "Kubrick",new Date(), new Date(), "male", "Computer Science", "stanley@kubrick.com")));
            log.info("fill DB: " + studentRepository.save(new Student(1984L, "George", "Orwell", new Date(), new Date(), "male", "Mathematics", "george@orwell.com")));
            log.info("fill DB: " + staffRepository.save(new Staff(1000L, "David", "Lynch", new Date(), "male", "david@Lynch.de")));
        };
    }

    // Fix the CORS errors
    @Bean
    public FilterRegistrationBean simpleCorsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // *** URL below needs to match the Vue client URL and port ***
        config.setAllowedOrigins(Collections.singletonList("http://localhost:8081"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

}
