package com.betest.avows.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.betest.avows.dtos.ClassroomDto;
import com.betest.avows.dtos.StudentDto;
import com.betest.avows.kafka.KafkaProducer;
import com.betest.avows.kafka.KafkaTopic.TopicEnum;
import com.betest.avows.models.Classroom;
import com.betest.avows.models.Student;
import com.betest.avows.repositories.ClassroomRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClassroomService {
    private final ClassroomRepository classroomRepository;
    private final StudentService studentService;

    private final KafkaProducer kafkaProducer;

    public ClassroomService(
            ClassroomRepository classroomRepository,
            StudentService studentService,
            KafkaProducer kafkaProducer) {
        this.classroomRepository = classroomRepository;
        this.studentService = studentService;
        this.kafkaProducer = kafkaProducer;
    }

    public Classroom saveClassroom(ClassroomDto classroomDto) {
        Classroom entity = new Classroom(classroomDto.name());
        Classroom savedEntity = classroomRepository.save(entity);

        kafkaProducer.sendMessage(TopicEnum.CLASSROOM, savedEntity);
        return savedEntity;
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

    public void classroomEnrollment(UUID classroomId, List<StudentDto> studentDtos) {
        Classroom classroom = getClassroomById(classroomId);
        List<Student> prevList = classroom.getStudents();

        List<Student> nextList = studentDtos.stream()
                .map((student) -> studentService.getById(student.id()))
                .collect(Collectors.toList());

        Set<Student> mergedStudent = new HashSet<>();
        mergedStudent.addAll(prevList);
        mergedStudent.addAll(nextList);

        classroom.setStudents(new ArrayList<>(mergedStudent));

        classroomRepository.save(classroom);
    }
}
