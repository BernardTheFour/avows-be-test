package com.betest.avows.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(generator = "students_uuid")
    @Column(unique = true, name = "id")
    UUID id;

    @Column(unique = true, name = "nisn")
    @Pattern(regexp = "\\d+", message = "NISN must consist of numbers only")
    String nisn;

    @Column(name = "name")
    String name;

    @ManyToOne()
    @JoinColumn(name = "classroom_id")
    Classroom classroom;

    public Student() {
    }

    public Student(String nisn, String name) {
        this.nisn = nisn;
        this.name = name;
    }

    public Student(UUID id, String nisn, String name) {
        this.id = id;
        this.nisn = nisn;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNisn() {
        return nisn;
    }

    public void setNisn(String nisn) {
        this.nisn = nisn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }
}
