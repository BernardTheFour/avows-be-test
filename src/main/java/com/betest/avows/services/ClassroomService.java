package com.betest.avows.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.betest.avows.dtos.ClassroomDto;
import com.betest.avows.models.Classroom;
import com.betest.avows.repositories.ClassroomRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClassroomService {
    private final ClassroomRepository classroomRepository;

    public ClassroomService(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    public Classroom saveClassroom(ClassroomDto classroomDto) {
        Classroom entity = new Classroom(classroomDto.id(), classroomDto.name());

        return classroomRepository.save(entity);
    }

    public Classroom getClassroomById(UUID id) {
        Classroom entity = classroomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Classroom not found"));

        return entity;
    }

    public List<Classroom> getAll() {
        List<Classroom> entities = classroomRepository.findAll();

        return entities;
    }
}
