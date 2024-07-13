package com.betest.avows.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.betest.avows.services.ClassroomService;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/classroom")
public class ClassroomController {

    private final ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

}
