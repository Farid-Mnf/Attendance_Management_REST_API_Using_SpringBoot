package com.farid.attendancesystem.service;

import com.farid.attendancesystem.dto.EnrollmentDTO;
import com.farid.attendancesystem.entity.Course;
import com.farid.attendancesystem.entity.Enrollment;
import com.farid.attendancesystem.entity.Student;
import com.farid.attendancesystem.repository.CourseRepository;
import com.farid.attendancesystem.repository.EnrollmentRepository;
import com.farid.attendancesystem.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnrollmentServiceTest {
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private EnrollmentRepository enrollmentRepository;
    @InjectMocks
    private EnrollmentService enrollmentService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(EnrollmentServiceTest.class);
    }

    @Test
    void getEnrollment() {
        UUID uuid = UUID.randomUUID();
        Enrollment enrollment = Enrollment.builder().id(uuid).enrollmentDate(LocalDate.now())
                        .student(Student.builder().name("farid").id(UUID.randomUUID()).email("farid@gmail.com").build())
                        .course(Course.builder().id(UUID.randomUUID()).name("databases").description("description").build())
                        .build();

        when(enrollmentRepository.findById(uuid)).thenReturn(Optional.of(enrollment));

        EnrollmentDTO enrollmentDTO = enrollmentService.getEnrollment(uuid);

        assertEquals(enrollment.getId(), enrollmentDTO.getId());
        assertEquals(enrollment.getEnrollmentDate(), enrollmentDTO.getEnrollmentDate());
        assertEquals(enrollment.getCourse().getId(), enrollmentDTO.getCourseDTO().getId());
        assertEquals(enrollment.getCourse().getName(), enrollmentDTO.getCourseDTO().getName());
        assertEquals(enrollment.getCourse().getDescription(), enrollmentDTO.getCourseDTO().getDescription());
        assertEquals(enrollment.getStudent().getName(), enrollmentDTO.getStudentDTO().getName());
        assertEquals(enrollment.getStudent().getEmail(), enrollmentDTO.getStudentDTO().getEmail());
        verify(enrollmentRepository, times(1)).findById(uuid);


    }

    @Test
    void addEnrollment() {
        UUID studentId = UUID.randomUUID();
        UUID courseId = UUID.randomUUID();
        Student student = Student.builder().id(studentId).name("student name").email("student@mail.com").build();
        Course course = Course.builder().id(courseId).name("course name").description("description").build();
        Enrollment enrollment = Enrollment.builder().enrollmentDate(LocalDate.now())
                        .course(course).student(student).build();

        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
        when(courseRepository.findById(course.getId())).thenReturn(Optional.of(course));

        when(enrollmentRepository.save(enrollment)).thenReturn(enrollment);


        EnrollmentDTO enrollmentDTOResult = enrollmentService.addEnrollment(studentId, courseId);

        assertNotNull(enrollmentDTOResult);
        assertEquals(student.getName(), enrollmentDTOResult.getStudentDTO().getName());
        assertEquals(student.getEmail(), enrollmentDTOResult.getStudentDTO().getEmail());
        assertEquals(student.getId(), enrollmentDTOResult.getStudentDTO().getId());

        assertEquals(course.getId(), enrollmentDTOResult.getCourseDTO().getId());
        assertEquals(course.getName(), enrollmentDTOResult.getCourseDTO().getName());
        assertEquals(course.getDescription(), enrollmentDTOResult.getCourseDTO().getDescription());
        assertEquals(enrollment.getId(), enrollmentDTOResult.getId());
        assertEquals(enrollment.getEnrollmentDate(), enrollmentDTOResult.getEnrollmentDate());
    }
}