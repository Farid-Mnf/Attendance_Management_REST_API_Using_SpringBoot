package com.farid.attendancesystem.controller;

import com.farid.attendancesystem.entity.Instructor;
import com.farid.attendancesystem.service.InstructorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/")
public class InstructorController {
    private final InstructorService instructorService;

    @GetMapping("instructor/{id}")
    public ResponseEntity<Instructor> getInstructor(@PathVariable("id") UUID uuid){
        return new ResponseEntity<Instructor>(instructorService.getInstructor(uuid), HttpStatus.OK);
    }

    @GetMapping("instructor")
    public ResponseEntity<List<Instructor>> getAllInstructor(){
        return new ResponseEntity<List<Instructor>>(instructorService.getAllInstructors(), HttpStatus.OK);
    }

    @PostMapping("instructor")
    public ResponseEntity<Instructor> createInstructor(@RequestBody Instructor instructor){
        return new ResponseEntity<>(instructorService.addInstructor(
                instructor.getName(),
                instructor.getEmail(),
                instructor.getPassword()), HttpStatus.CREATED);
    }

    @DeleteMapping("instructor/{id}")
    public ResponseEntity<String> deleteInstructor(@PathVariable("id") UUID uuid){
        String instructorName = instructorService.getInstructor(uuid).getName();
        instructorService.removeInstructor(uuid);
        return new ResponseEntity<>(instructorName, HttpStatus.OK);
    }

    @PutMapping("instructor/{id}")
    public ResponseEntity<Instructor> updateInstructor(@PathVariable("id") UUID uuid, @RequestBody Instructor instructor){
        instructor = instructorService.updateInstructor(uuid, instructor);
        return new ResponseEntity<Instructor>(instructor, HttpStatus.OK);
    }
}