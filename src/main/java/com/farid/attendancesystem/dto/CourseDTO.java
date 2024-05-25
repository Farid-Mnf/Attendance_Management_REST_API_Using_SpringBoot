package com.farid.attendancesystem.dto;

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
public class CourseDTO {
    private UUID id;
    private String name;
    private String description;
    private InstructorDTO instructorDTO;
    private List<LectureDTO> lectureDTOS;
    private List<EnrollmentDTO> enrollmentDTOS;
}
