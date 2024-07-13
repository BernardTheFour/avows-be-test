package com.betest.avows.models;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "classrooms")
public class Classroom {
    
    @Id
    @GeneratedValue(generator = "classrooms_uuid")
    @Column(unique = true, name = "id")
    UUID id;

    @Column(unique = true, name = "name")
    String name;

    @OneToMany(mappedBy = "classroom", fetch = FetchType.LAZY)
    List<Student> students;

    public Classroom() {
    }

    public Classroom(String name) {
        this.name = name;
    }

    public Classroom(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
