package com.betest.avows.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.betest.avows.dtos.StudentDto;
import com.betest.avows.models.Student;
import com.betest.avows.repositories.StudentRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student saveStudent(StudentDto studentDto) {
        Student entity = new Student(studentDto.name(), studentDto.nisn());
        return studentRepository.save(entity);
    }

    public List<Student> saveAllStudent(List<Student> entities) {
        return studentRepository.saveAll(entities);
    }

    public Student getById(UUID id) {
        Student entity = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

        return entity;
    }

    public List<Student> getAll() {
        List<Student> entities = studentRepository.findAll();

        return entities;
    }
}
