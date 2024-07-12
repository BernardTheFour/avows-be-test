package com.betest.avows.services;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.betest.avows.repositories.StudentRepository;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    
    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository mockStudentRepository;
}
