package com.example.demo.spring.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Classroom {

    private String name;

    private List<Student> students;

    public void info() {
        System.out.println("class " + name + " : ");
        students.forEach(Student::info);
    }
}
