package com.farid.attendancesystem.service;

import com.farid.attendancesystem.dto.CourseDTO;
import com.farid.attendancesystem.entity.Course;
import com.farid.attendancesystem.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class CourseServiceTest {
    @Mock
    private CourseRepository courseRepository;
    @InjectMocks
    private CourseService courseService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(CourseServiceTest.class);
    }

    @Test
    void addCourse() {
        Course course = Course.builder().name("Data Intensive Applications").description("how to design data").build();

        when(courseRepository.save(any(Course.class))).thenReturn(course);

        CourseDTO courseDTO = courseService.addCourse(course.getName(), course.getDescription());

        assertEquals(course.getId(), courseDTO.getId());
        assertEquals(course.getName(), courseDTO.getName());
        assertEquals(course.getDescription(), courseDTO.getDescription());

        verify(courseRepository, times(1)).save(course);
    }

    @Test
    void updateCourse() {
        UUID uuid = UUID.randomUUID();
        Course course = Course.builder().name("Databases").description("how to design databases").build();

        when(courseRepository.existsById(uuid)).thenReturn(true);
        when(courseRepository.findById(uuid)).thenReturn(Optional.of(course));
        when(courseRepository.save(course)).thenReturn(course);

        CourseDTO updatedCourseDTO = CourseDTO.builder().name("Database Fundamentals").description(course.getDescription()).build();

        CourseDTO courseDTO = courseService.updateCourse(uuid, updatedCourseDTO);

        assertEquals(updatedCourseDTO.getName(), courseDTO.getName());
        assertEquals(updatedCourseDTO.getDescription(), courseDTO.getDescription());

        verify(courseRepository, times(1)).existsById(any(UUID.class));
        verify(courseRepository, times(1)).findById(any(UUID.class));
        verify(courseRepository, times(1)).save(any(Course.class));

    }

    @Test
    void getCourse() {
        Course course = Course.builder().id(UUID.randomUUID()).name("Name").description("Description").build();

        when(courseRepository.findById(course.getId())).thenReturn(Optional.of(course));

        CourseDTO courseDTO = courseService.getCourse(course.getId());

        assertEquals(course.getName(), courseDTO.getName());
        assertEquals(course.getId(), courseDTO.getId());
        assertEquals(course.getDescription(), courseDTO.getDescription());
        verify(courseRepository, times(1)).findById(course.getId());
    }
}