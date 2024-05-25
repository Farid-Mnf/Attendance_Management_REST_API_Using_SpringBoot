package com.farid.attendancesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollmentDTO {
    private UUID id;
    private StudentDTO studentDTO;
    private CourseDTO courseDTO;
    private LocalDate enrollmentDate;
}
