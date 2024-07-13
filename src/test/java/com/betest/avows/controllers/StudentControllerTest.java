package com.betest.avows.controllers;

import static org.mockito.Mockito.doReturn;

import java.net.URI;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.betest.avows.dtos.StudentDto;
import com.betest.avows.models.Student;
import com.betest.avows.services.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = StudentController.class)
@AutoConfigureMockMvc(addFilters = false)
public class StudentControllerTest {

    @MockBean
    private StudentService mockStudentService;

    @Autowired
    private MockMvc mockRequest;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("test: get student by their id")
    void testGetStudentById() throws Exception {
        // input
        UUID inputId = UUID.randomUUID();

        // stub result
        Student result = new Student(inputId, "00123", "name");
        doReturn(result)
                .when(mockStudentService)
                .getById(inputId);

        // test
        StudentDto resultDto = StudentDto.toDto(result);

        URI uri = new URI("/student/id/" + inputId);
        mockRequest
                .perform(MockMvcRequestBuilders.get(uri))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(objectMapper.writeValueAsString(resultDto)));
    }
}
