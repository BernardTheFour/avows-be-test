package com.betest.avows.dtos;

import java.util.Objects;
import java.util.UUID;

import com.betest.avows.models.Student;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.constraints.Pattern;

@JsonInclude(value = Include.NON_NULL)
public record StudentDto(
        UUID id,
        @Pattern(regexp = "\\d+", message = "NISN number only") String nisn,
        String name,
        ClassroomDto classroom) {

    public static StudentDto toDtoDetached(Student entity) {
        if (Objects.isNull(entity)) {
            return null;
        }

        return new StudentDto(
                entity.getId(),
                entity.getNisn(),
                entity.getName(),
                null);
    }

    public static StudentDto toDto(Student entity) {
        ClassroomDto classroomDto = ClassroomDto
                // when chaining dto, avoid circular json printing
                .toDtoDetached(entity.getClassroom());

        return new StudentDto(
                entity.getId(),
                entity.getNisn(),
                entity.getName(),
                classroomDto);
    }
}
