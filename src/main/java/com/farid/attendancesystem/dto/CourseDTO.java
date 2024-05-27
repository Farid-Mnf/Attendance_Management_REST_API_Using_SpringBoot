package com.farid.attendancesystem.dto;

import com.farid.attendancesystem.entity.Course;
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
    public CourseDTO(UUID id, String name, String description){
        this(id, name, description, null, null, null);
    }
}
