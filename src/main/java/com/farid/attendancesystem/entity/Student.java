package com.farid.attendancesystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String email; // remove or what ? any other properties?

    @OneToMany(mappedBy = "student")
    private List<AttendanceRecord> attendanceRecords;
    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments;

}
