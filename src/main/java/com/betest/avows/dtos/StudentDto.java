package com.betest.avows.dtos;

import java.util.UUID;

import com.betest.avows.models.Student;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.constraints.Pattern;

@JsonInclude(value = Include.NON_NULL)
public record StudentDto(
        UUID id,
        @Pattern(regexp = "\\d+", message = "NISN number only") String nisn,
        String name) {

    public static StudentDto toDto(Student entity) {
        return new StudentDto(
                entity.getId(),
                entity.getNisn(),
                entity.getName());
    }
}
