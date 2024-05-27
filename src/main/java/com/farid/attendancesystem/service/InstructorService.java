package com.farid.attendancesystem.service;


import com.farid.attendancesystem.dto.InstructorDTO;
import com.farid.attendancesystem.entity.Instructor;
import com.farid.attendancesystem.repository.InstructorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class InstructorService {
    private final InstructorRepository instructorRepository;

    public void removeInstructor(UUID uuid){
        instructorRepository.deleteById(uuid);
    }

    public List<InstructorDTO> getAllInstructors(){
        List<InstructorDTO> instructorDTOS = new ArrayList<>();
        for(Instructor instructor: instructorRepository.findAll()){
           instructorDTOS.add(InstructorDTO.builder().id(instructor.getId()).name(instructor.getName())
                   .email(instructor.getEmail())
                   .password(instructor.getPassword()).build());
        }
        return instructorDTOS;
    }

    public InstructorDTO updateInstructor(UUID uuid, InstructorDTO updatedInstructor){
        if (!instructorRepository.existsById(uuid)) {
            throw new RuntimeException("Instructor not found with ID: " + uuid);
        }

        Instructor existingInstructor = instructorRepository.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Instructor not found with ID: " + uuid));

        existingInstructor.setName(updatedInstructor.getName());
        existingInstructor.setEmail(updatedInstructor.getEmail());
        existingInstructor.setPassword(updatedInstructor.getPassword());

        instructorRepository.save(existingInstructor);
        return InstructorDTO.builder().id(uuid).name(existingInstructor.getName())
                .email(existingInstructor.getEmail())
                .password(existingInstructor.getPassword()).build();
    }

    public InstructorDTO addInstructor(String name, String email, String password){
        Instructor instructor = new Instructor(UUID.randomUUID(), name, email, password, null);
        instructor = instructorRepository.save(instructor);
        return InstructorDTO.builder().id(instructor.getId())
                .name(instructor.getName())
                .email(instructor.getEmail())
                .password(instructor.getPassword()).build();
    }

    public InstructorDTO getInstructor(UUID uuid){
        Instructor instructor = instructorRepository.findById(uuid).orElseThrow(() -> new RuntimeException("instructor not found with id: " + uuid));
        return InstructorDTO.builder().id(instructor.getId())
                .name(instructor.getName())
                .email(instructor.getEmail())
                .password(instructor.getPassword()).build();
    }
}
