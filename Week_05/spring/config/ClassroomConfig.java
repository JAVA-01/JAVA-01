package com.example.demo.spring.config;


import com.example.demo.spring.bean.Classroom;
import com.example.demo.spring.bean.Student;
import lombok.Data;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
@Data
public class ClassroomConfig implements ApplicationContextAware{

    private ApplicationContext applicationContext;

    @Bean("class_B")
    public Classroom build() {
        /**获取到bean
         * */
        List<Student> students = new ArrayList<>();
        Optional<Object> student = Optional.ofNullable(applicationContext.getBean("student_03"));
        student.ifPresent(obj -> students.add((Student) obj));

        student = Optional.ofNullable(applicationContext.getBean("student_04"));
        student.ifPresent(obj -> students.add((Student) obj));
        return new Classroom("B", students);
    }
}
