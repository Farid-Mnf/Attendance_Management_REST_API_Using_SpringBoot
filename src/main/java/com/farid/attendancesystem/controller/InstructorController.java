package com.farid.attendancesystem.controller;

import com.farid.attendancesystem.config.JwtService;
import com.farid.attendancesystem.controller.requestdtos.LoginRequest;
import com.farid.attendancesystem.dto.CourseDTO;
import com.farid.attendancesystem.dto.InstructorDTO;
import com.farid.attendancesystem.service.InstructorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/")
public class InstructorController {
    private final AuthenticationManager authenticationManager;
    private final InstructorService instructorService;
    private final JwtService jwtService;

    @GetMapping("instructor/{id}")
    public ResponseEntity<InstructorDTO> getInstructor(@PathVariable("id") UUID uuid){
        return new ResponseEntity<>(instructorService.getInstructor(uuid), HttpStatus.OK);
    }

    @GetMapping("instructor")
    public ResponseEntity<List<InstructorDTO>> getAllInstructor(){
        return new ResponseEntity<>(instructorService.getAllInstructors(), HttpStatus.OK);
    }

    @PostMapping("instructor/register")
    public ResponseEntity<InstructorDTO> createInstructor(@RequestBody InstructorDTO instructor){
        InstructorDTO instructorDTO = instructorService.saveInstructor(
                instructor.getName(),
                instructor.getEmail(),
                instructor.getPassword()
        );

        return ResponseEntity.ok(instructorDTO);

    }

    @PostMapping("instructor/login")
    public ResponseEntity<String> loginInstructor(@RequestBody LoginRequest loginRequest){
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()
            ));

            String jwt = jwtService.generateJwtToken(authentication);
            return ResponseEntity.ok("Bearer " + jwt);
        }catch (Exception e){
            return new ResponseEntity<>("Forbidden", HttpStatus.UNAUTHORIZED);
        }
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