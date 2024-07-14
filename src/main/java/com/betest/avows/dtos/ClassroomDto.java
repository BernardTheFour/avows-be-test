package com.betest.avows.dtos;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import com.betest.avows.models.Classroom;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public record ClassroomDto(
        UUID id,
        String name,
        List<StudentDto> students) {

    public static ClassroomDto toDtoDetached(Classroom entity) {
        if (Objects.isNull(entity)){
            return null;
        }

        return new ClassroomDto(
                entity.getId(),
                entity.getName(),
                null);
    }

    public static ClassroomDto toDto(Classroom entity) {
        List<StudentDto> studentDtos = entity.getStudents()
                .stream()
                // when chaining dto, avoid circular json printing
                .map(StudentDto::toDtoDetached)
                .collect(Collectors.toList());

        return new ClassroomDto(
                entity.getId(),
                entity.getName(),
                studentDtos);
    }
}
