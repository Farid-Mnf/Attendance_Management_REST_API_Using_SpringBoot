package com.farid.attendancesystem.service;


import com.farid.attendancesystem.entity.Instructor;
import com.farid.attendancesystem.repository.InstructorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class InstructorService {
    private final InstructorRepository instructorRepository;

    public void removeInstructor(UUID uuid){
        instructorRepository.deleteById(uuid);
    }

    public List<Instructor> getAllInstructors(){
        return instructorRepository.findAll();
    }

    public Instructor updateInstructor(UUID uuid, Instructor updatedInstructor){
        if (!instructorRepository.existsById(uuid)) {
            throw new RuntimeException("Instructor not found with ID: " + uuid);
        }

        Instructor existingInstructor = instructorRepository.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Instructor not found with ID: " + uuid));

        existingInstructor.setName(updatedInstructor.getName());
        existingInstructor.setEmail(updatedInstructor.getEmail());
        existingInstructor.setPassword(updatedInstructor.getPassword());

        return instructorRepository.save(existingInstructor);
    }

    public Instructor addInstructor(String name, String email, String password){
        Instructor instructor = new Instructor(UUID.randomUUID(), name, email, password, null);
        instructor = instructorRepository.save(instructor);
        return instructor;
    }

    public Instructor getInstructor(UUID uuid){
        return instructorRepository.findById(uuid).orElseThrow(() -> new RuntimeException("instructor not found with id: " + uuid));
    }
}
