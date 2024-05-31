package com.farid.attendancesystem.controller;

import com.farid.attendancesystem.dto.CourseDTO;
import com.farid.attendancesystem.dto.InstructorDTO;
import com.farid.attendancesystem.service.InstructorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/")
public class InstructorController {
    private final InstructorService instructorService;

    @GetMapping("instructor/{id}")
    public ResponseEntity<InstructorDTO> getInstructor(@PathVariable("id") UUID uuid){
        return new ResponseEntity<>(instructorService.getInstructor(uuid), HttpStatus.OK);
    }

    @GetMapping("instructor")
    public ResponseEntity<List<InstructorDTO>> getAllInstructor(){
        return new ResponseEntity<>(instructorService.getAllInstructors(), HttpStatus.OK);
    }

    @PostMapping("instructor")
    public ResponseEntity<InstructorDTO> createInstructor(@RequestBody InstructorDTO instructor){
        return new ResponseEntity<>(instructorService.addInstructor(
                instructor.getName(),
                instructor.getEmail(),
                instructor.getPassword()), HttpStatus.CREATED);
    }
    @PostMapping("instructor/{id}/course")
    public ResponseEntity<InstructorDTO> addCourseToInstructor(@PathVariable("id") UUID uuid, @RequestBody Map<String, UUID> body){
        return new ResponseEntity<>(instructorService.addCourseToInstructor(uuid, body.get("courseId")), HttpStatus.OK);
    }

    @GetMapping("instructor/{id}/course")
    public ResponseEntity<List<CourseDTO>> getInstructorCourses(@PathVariable("id") UUID uuid){
        return new ResponseEntity<>(
                instructorService.getInstructorCourses(uuid),
                HttpStatus.OK
        );
    }

    @DeleteMapping("instructor/{id}")
    public ResponseEntity<String> deleteInstructor(@PathVariable("id") UUID uuid){
        String instructorName = instructorService.getInstructor(uuid).getName();
        instructorService.removeInstructor(uuid);
        return new ResponseEntity<>(instructorName, HttpStatus.OK);
    }



    @PutMapping("instructor/{id}")
    public ResponseEntity<InstructorDTO> updateInstructor(@PathVariable("id") UUID uuid, @RequestBody InstructorDTO instructor){
        instructor = instructorService.updateInstructor(uuid, instructor);
        return new ResponseEntity<>(instructor, HttpStatus.OK);
    }
}