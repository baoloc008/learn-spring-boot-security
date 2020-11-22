package com.demo.learnspringbootsecurity.student;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {
    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1, "James Bond"),
            new Student(2, "Maria Jones"),
            new Student(3, "Anna Smith")
    );

    @GetMapping
    public static List<Student> getAllStudents() {
        return STUDENTS;
    }

    @PostMapping
    public String registerNewStudent(@RequestBody Student student) {
        System.out.println(student);
        return "register successful";
    }

    @DeleteMapping(path = "{studentId}")
    public String deleteStudent(@PathVariable("studentId") Integer studentId) {
        System.out.println(studentId);
        return "delete successful";
    }

    @PutMapping(path = "{studentId}")
    public String updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student student) {
        System.out.println(String.format("%s %s", studentId, student));
        return "update successful";
    }
}
