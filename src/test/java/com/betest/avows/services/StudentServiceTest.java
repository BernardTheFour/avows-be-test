package com.betest.avows.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;

import com.betest.avows.dtos.StudentDto;
import com.betest.avows.models.Student;
import com.betest.avows.repositories.StudentRepository;

@SpringBootTest()
@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository mockStudentRepository;

    private StudentService studentService;

    @BeforeEach
    public void setup() {
        studentService = new StudentService(mockStudentRepository);
    }

    @Test
    @DisplayName("test: save new student success")
    void testSaveNewStudentSuccessWhenValid() {
        // stub entity
        Student mockEntity = new Student(
                UUID.randomUUID(),
                "001122",
                "Name");
        doReturn(mockEntity)
                .when(mockStudentRepository)
                .save(any(Student.class));

        // input
        StudentDto studentDto = StudentDto.toDtoDetached(mockEntity);

        // test
        Student expectedValue = mockEntity;
        Student actualValue = studentService.saveStudent(studentDto);

        assertAll(() -> {
            assertEquals(studentDto.name(), mockEntity.getName());
            assertEquals(studentDto.nisn(), mockEntity.getNisn());
        });
        assertEquals(expectedValue, actualValue);
        verify(mockStudentRepository, atMostOnce()).save(any(Student.class));
    }

    @Test
    @DisplayName("test: save new student failed when exception thrown")
    void testSaveNewStudenFailedWhenExceptionThrown() {
        // stub entity
        Student mockEntity = new Student(
                UUID.randomUUID(),
                "001122",
                "Name");
        doThrow(DuplicateKeyException.class)
                .when(mockStudentRepository)
                .save(any(Student.class));

        // input
        StudentDto studentDto = StudentDto.toDtoDetached(mockEntity);

        // test
        assertThrows(DuplicateKeyException.class,
                () -> studentService.saveStudent(studentDto));
    }

    @Test
    @DisplayName("test: get student by id success when valid")
    void testGetStudentByIdSuccessWhenValid() {
        UUID studentId = UUID.randomUUID();
        // stub entity
        Student mockEntity = new Student(
                studentId,
                "001122",
                "Name");
        doReturn(Optional.of(mockEntity))
                .when(mockStudentRepository)
                .findById(studentId);

        // test
        Student expectedResult = mockEntity;
        Student actualResutl = studentService.getById(studentId);

        assertEquals(expectedResult, actualResutl);
        verify(mockStudentRepository, atMostOnce()).findById(studentId);
    }
}
