package com.farid.attendancesystem.controller;

import com.farid.attendancesystem.dto.CourseDTO;
import com.farid.attendancesystem.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/")
public class CourseController {

    private final CourseService courseService;


    @GetMapping("course/{id}")
    public ResponseEntity<CourseDTO> getCourse(@PathVariable("id") UUID uuid){
        return new ResponseEntity<>(courseService.getCourse(uuid), HttpStatus.OK);
    }

    @GetMapping("course")
    public ResponseEntity<List<CourseDTO>> getAllCourses(){
        return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.OK);
    }

    @PostMapping("course")
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO course){
        return new ResponseEntity<>(courseService.addCourse(
                course.getName(),
                course.getDescription()), HttpStatus.CREATED);
    }

    @DeleteMapping("course/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable("id") UUID uuid){
        String courseName = courseService.getCourse(uuid).getName();
        courseService.deleteCourse(uuid);
        return new ResponseEntity<>(courseName, HttpStatus.OK);
    }

    @PutMapping("course/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable("id") UUID uuid,
                                                       @RequestBody CourseDTO course
    ){
        course = courseService.updateCourse(uuid, course);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }
}