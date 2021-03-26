package com.example.demo.spring.config;

import com.example.demo.spring.bean.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

    @Bean("student_03")
    public Student build_03() {
        return new Student(3, "Malone", "B");
    }

}
