package com.farid.attendancesystem.controller;

import com.farid.attendancesystem.dto.StudentDTO;
import com.farid.attendancesystem.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping("student")
    public ResponseEntity<List<StudentDTO>> getAllStudents(){
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping("student/{id}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable("id")UUID uuid){
        return new ResponseEntity<>(studentService.getStudent(uuid), HttpStatus.OK);
    }

    @PostMapping("student")
    public ResponseEntity<StudentDTO> addStudent(@RequestBody StudentDTO studentDTO){
        studentDTO = studentService.addStudent(studentDTO.getName(), studentDTO.getEmail());
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }
}






