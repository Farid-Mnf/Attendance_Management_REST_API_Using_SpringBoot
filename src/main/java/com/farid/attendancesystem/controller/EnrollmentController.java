package com.farid.attendancesystem.controller;

import com.farid.attendancesystem.controller.requestdtos.EnrollmentRequestDTO;
import com.farid.attendancesystem.dto.EnrollmentDTO;
import com.farid.attendancesystem.service.EnrollmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/enrollment")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @GetMapping("/{id}")
    public EnrollmentDTO getEnrollment(@PathVariable("id")UUID uuid){
        return enrollmentService.getEnrollment(uuid);
    }
    @PostMapping
    public EnrollmentDTO addEnrollment(@RequestBody EnrollmentRequestDTO enrollmentRequestDTO){
         return enrollmentService.addEnrollment(enrollmentRequestDTO.getStudentId(),
                enrollmentRequestDTO.getCourseId());
    }
    @GetMapping
    public List<EnrollmentDTO> getEnrollments(){
        return enrollmentService.getEnrollments();
    }
}
