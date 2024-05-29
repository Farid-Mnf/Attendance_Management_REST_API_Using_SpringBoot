package com.farid.attendancesystem.service;

import com.farid.attendancesystem.dto.CourseDTO;
import com.farid.attendancesystem.dto.EnrollmentDTO;
import com.farid.attendancesystem.dto.StudentDTO;
import com.farid.attendancesystem.entity.Course;
import com.farid.attendancesystem.entity.Enrollment;
import com.farid.attendancesystem.entity.Student;
import com.farid.attendancesystem.repository.CourseRepository;
import com.farid.attendancesystem.repository.EnrollmentRepository;
import com.farid.attendancesystem.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    public List<EnrollmentDTO> getEnrollments(){
        return enrollmentRepository.findAll().stream().map(this::mapToEnrollmentDTO).collect(Collectors.toList());
    }

    public EnrollmentDTO getEnrollment(UUID uuid){
        return mapToEnrollmentDTO(enrollmentRepository.findById(uuid).orElseThrow());
    }

    public EnrollmentDTO addEnrollment(UUID studentId, UUID courseId){
        Student student = studentRepository.findById(studentId).orElseThrow();
        Course course = courseRepository.findById(courseId).orElseThrow();

        return mapToEnrollmentDTO(enrollmentRepository.save(
                Enrollment.builder()
                        .enrollmentDate(LocalDate.now())
                        .student(student)
                        .course(course)
                        .build())
        );
    }

    public EnrollmentDTO mapToEnrollmentDTO(Enrollment enrollment){
        return EnrollmentDTO.builder()
                .id(enrollment.getId())
                .enrollmentDate(LocalDate.now())
                .studentDTO(
                        StudentDTO.builder()
                                .name(enrollment.getStudent().getName())
                                .email(enrollment.getStudent().getEmail())
                                .id(enrollment.getStudent().getId())
                                .build()
                )
                .courseDTO(
                        CourseDTO.builder()
                                .id(enrollment.getCourse().getId())
                                .description(enrollment.getCourse().getDescription())
                                .name(enrollment.getCourse().getName())
                                .build()
                ).build();
    }
}
