package com.farid.attendancesystem.service;

import com.farid.attendancesystem.entity.Instructor;
import com.farid.attendancesystem.repository.InstructorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class InstructorServiceTest {
    @Mock
    private InstructorRepository instructorRepository;

    @InjectMocks
    private InstructorService instructorService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    public void getInstructor() {
//        UUID uuid = UUID.randomUUID();
//        Instructor instructor = new Instructor(uuid, "Ahmed", "ah@gmail.com", "pass123", null);
//
//        when(instructorRepository.findById(uuid)).thenReturn(Optional.of(instructor));
//
//        Instructor foundInstructor = instructorService.getInstructor(uuid);
//        assertEquals("ah@gmail.com", foundInstructor.getEmail());
//    }

    @Test
    void removeInstructor() {
    }

    @Test
    void getAllInstructors() {
    }

    @Test
    void updateInstructor() {
    }

    @Test
    void addInstructor() {
    }

}