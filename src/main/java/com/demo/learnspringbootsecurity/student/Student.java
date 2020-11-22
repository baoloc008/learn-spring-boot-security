package com.demo.learnspringbootsecurity.student;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Student {
    private final Integer studentId;
    private final String studentName;
}
