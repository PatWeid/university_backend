package com.example.university_backend;

import com.example.university_backend.entity.Student;
import com.example.university_backend.entity.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UniversityBackendApplication {

    private static final Logger log = LoggerFactory.getLogger("myApp");
    public static void main(String[] args) {
        SpringApplication.run(UniversityBackendApplication.class, args);
    }



    @Bean
    CommandLineRunner initDataBase(StudentRepository repository) {
        return args -> {
            log.info("fill DB: " + repository.save(new Student("Stanley", "Kubrick")));
            log.info("fill DB: " + repository.save(new Student("George", "Orwell")));
        };
    }

}
