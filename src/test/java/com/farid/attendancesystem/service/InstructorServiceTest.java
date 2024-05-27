package com.farid.attendancesystem.service;

import com.farid.attendancesystem.repository.InstructorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class InstructorServiceTest {
    @Mock
    private InstructorRepository instructorRepository;

    @InjectMocks
    private InstructorService instructorService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


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