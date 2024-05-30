package com.farid.attendancesystem.controller;

import com.farid.attendancesystem.dto.AttendanceRecordDTO;
import com.farid.attendancesystem.dto.EnrollmentDTO;
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
    @GetMapping("student/{id}/attendance-records")
    public ResponseEntity<List<AttendanceRecordDTO>> getStudentAttendanceRecords(@PathVariable("id") UUID uuid){
        return new ResponseEntity<>(studentService.getStudent(uuid).getAttendanceRecordDTOS(), HttpStatus.OK);
    }

    @GetMapping("student/{id}/enrollments")
    public ResponseEntity<List<EnrollmentDTO>> getStudentEnrollments(@PathVariable("id") UUID uuid){
        return new ResponseEntity<>(studentService.getStudent(uuid).getEnrollmentDTOS(), HttpStatus.OK);
    }
    @PostMapping("student")
    public ResponseEntity<StudentDTO> addStudent(@RequestBody StudentDTO studentDTO){
        studentDTO = studentService.addStudent(studentDTO.getName(), studentDTO.getEmail());
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }
    @DeleteMapping("student/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") UUID uuid){
        try{
            studentService.deleteStudent(uuid);
            return new ResponseEntity<>("deleted successfully!", HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/student/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable("id") UUID id,
                                                    @RequestBody StudentDTO studentDTO){
        studentDTO = studentService.updateStudent(id, studentDTO);
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }
}






