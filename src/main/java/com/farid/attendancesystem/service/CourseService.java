package com.farid.attendancesystem.service;

import com.farid.attendancesystem.dto.CourseDTO;
import com.farid.attendancesystem.dto.LectureDTO;
import com.farid.attendancesystem.entity.Course;
import com.farid.attendancesystem.entity.Lecture;
import com.farid.attendancesystem.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseDTO addCourse(String name, String description){
        Course course = Course.builder().name(name).description(description).build();
        course = courseRepository.save(course);
        return CourseDTO.builder().name(course.getName()).id(course.getId()).description(course.getDescription()).build();
    }

    public void deleteCourse(UUID id){
        if(courseRepository.existsById(id)){
            courseRepository.deleteById(id);
        }
    }

    public CourseDTO updateCourse(UUID id, CourseDTO updatedCourse){
        if(courseRepository.existsById(id)){
            Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("course not found with id: " + id));
            course.setName(updatedCourse.getName());
            course.setDescription(updatedCourse.getDescription());
            course = courseRepository.save(course);
            return CourseDTO.builder().id(course.getId()).name(course.getName()).description(course.getDescription()).build();
        }
        return null;
    }

    public CourseDTO getCourse(UUID id){
        Course course;
        course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("course not found with id: " + id));
        return CourseDTO.builder().id(course.getId()).name(course.getName()).description(course.getDescription()).build();
    }

    public List<CourseDTO> getAllCourses() {
        List<CourseDTO> courseDTOS = new ArrayList<>();
        for(Course course: courseRepository.findAll()){
            courseDTOS.add(CourseDTO.builder().id(course.getId()).name(course.getName())
                    .description(course.getDescription())
                    .build());
        }
        return courseDTOS;
    }

    public List<LectureDTO> getCourseLectures(UUID uuid) {
        Course course = courseRepository.findById(uuid).orElseThrow();

        List<LectureDTO> lectureDTOS = new ArrayList<>();
        if(course.getLectures() != null)
            for(Lecture lecture: course.getLectures()){
                lectureDTOS.add(
                        LectureDTO.builder()
                                .id(lecture.getId())
                                .title(lecture.getTitle())
                                .dateTime(lecture.getDateTime())
                                .courseDTO(
                                        CourseDTO.builder()
                                                .id(lecture.getCourse().getId())
                                                .name(lecture.getCourse().getName())
                                                .description(lecture.getCourse().getDescription())
                                                .build()
                                ).build()
                );
            }
        return lectureDTOS;
    }
}
