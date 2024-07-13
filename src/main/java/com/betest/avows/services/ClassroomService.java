package com.betest.avows.services;

import org.springframework.stereotype.Service;

import com.betest.avows.repositories.ClassroomRepository;

@Service
public class ClassroomService {
    private final ClassroomRepository classroomRepository;

    public ClassroomService(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

}
