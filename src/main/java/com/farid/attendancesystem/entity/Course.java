package com.farid.attendancesystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String description;
    @ManyToOne
    private Instructor instructor;
    @OneToMany(mappedBy = "course")
    private List<Lecture> lectures;
    @OneToMany(mappedBy = "course")
    private List<Enrollment> enrollments;

    public Course(UUID id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
