package com.example.demo.spring.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {

    private int id;

    private String name;

    private String classNo;

    public void info() {
        System.out.println("student-->id:[" + id + "],name:[" + name + "],classNo:[" + classNo + "]");
    }
}
