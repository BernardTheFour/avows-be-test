package com.betest.avows.controllers;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betest.avows.dtos.StudentDto;
import com.betest.avows.models.Student;
import com.betest.avows.services.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/id/{uuid}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable(name = "uuid") UUID uuid) {
        Student studentEntity = studentService.getById(uuid);
        StudentDto studentDto = StudentDto.toDtoDetached(studentEntity);

        return ResponseEntity.ok(studentDto);
    }
}
