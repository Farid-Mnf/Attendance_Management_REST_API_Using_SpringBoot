package com.farid.attendancesystem.service;

import com.farid.attendancesystem.entity.Course;
import com.farid.attendancesystem.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public Course addCourse(String name, String description){
        Course course = new Course(UUID.randomUUID(), name, description);
        course = courseRepository.save(course);
        return course;
    }

    public Course deleteCourse(UUID id){
        Course course;
        if(courseRepository.existsById(id)){
            course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("course not found with id: " + id));
            return course;
        }
        return null;
    }

    public Course updateCourse(UUID id, Course updatedCourse){
        Course course = null;
        if(courseRepository.existsById(id)){
            course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("course not found with id: " + id));
            course.setName(updatedCourse.getName());
            course.setDescription(updatedCourse.getDescription());
            return courseRepository.save(course);
        }
        return course;
    }

    public Course getCourse(UUID id){
        Course course = null;
        course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("course not found with id: " + id));
        return course;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}
