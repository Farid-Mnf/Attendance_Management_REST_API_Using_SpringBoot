package com.farid.attendancesystem.service;

import com.farid.attendancesystem.dto.InstructorDTO;
import com.farid.attendancesystem.entity.Instructor;
import com.farid.attendancesystem.repository.InstructorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InstructorServiceTest {
    @Mock
    private InstructorRepository instructorRepository;

    @InjectMocks
    private InstructorService instructorService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(InstructorServiceTest.class);
    }


    @Test
    void removeInstructor() {
        UUID uuid = UUID.randomUUID();
        instructorService.removeInstructor(uuid);

        verify(instructorRepository, times(1)).deleteById(uuid);

    }

    @Test
    void getInstructor(){
        UUID uuid = UUID.randomUUID();
        Instructor instructor = Instructor.builder().id(uuid).name("Farid").email("farid@gmail.com").build();
        when(instructorRepository.findById(any(UUID.class))).thenReturn(Optional.of(instructor));


        InstructorDTO instructorDTO = instructorService.getInstructor(uuid);
        assertEquals(uuid, instructorDTO.getId());
        assertEquals(instructor.getName(), instructorDTO.getName());
        assertEquals(instructor.getEmail(), instructorDTO.getEmail());
        verify(instructorRepository, times(1)).findById(uuid);
    }
    @Test
    void getAllInstructors() {
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        Instructor instructor1 = Instructor.builder().id(uuid1).name("Farid").build();
        Instructor instructor2 = Instructor.builder().id(uuid2).name("Mahmoud").build();

        when(instructorRepository.findAll()).thenReturn(List.of(instructor1, instructor2));

        List<InstructorDTO> instructorDTOS = instructorService.getAllInstructors();

        assertEquals(2, instructorDTOS.size());
        assertEquals(instructor1.getId(), instructorDTOS.get(0).getId());
        assertEquals(instructor1.getName(), instructorDTOS.get(0).getName());
        assertEquals(instructor2.getId(), instructorDTOS.get(1).getId());
        assertEquals(instructor2.getName(), instructorDTOS.get(1).getName());

    }

    @Test
    void updateInstructor() {
        UUID uuid = UUID.randomUUID();
        Instructor instructor = Instructor.builder().id(uuid).name("Farid").email("farid@gmail.com")
                .password("password").build();
        when(instructorRepository.existsById(uuid)).thenReturn(true);
        when(instructorRepository.findById(uuid)).thenReturn(Optional.of(instructor));
        when(instructorRepository.save(instructor)).thenReturn(instructor);

        InstructorDTO updatedInstructor = InstructorDTO.builder().id(instructor.getId())
                .name(instructor.getName())
                .email(instructor.getEmail().replace("gmail", "hotmail"))
                .password(instructor.getPassword()).build();

        InstructorDTO resultDTO = instructorService.updateInstructor(uuid, updatedInstructor);

        assertEquals(updatedInstructor.getId(), resultDTO.getId());
        assertEquals(updatedInstructor.getName(), resultDTO.getName());
        assertEquals(updatedInstructor.getEmail(), resultDTO.getEmail());
        assertEquals(updatedInstructor.getPassword(), resultDTO.getPassword());


    }

    @Test
    void addInstructor() {
        UUID uuid = UUID.randomUUID();
        Instructor instructor = Instructor.builder()
                        .id(uuid).name("Farid").email("farid@gmail.com").password("password").build();

        when(instructorRepository.save(any(Instructor.class))).thenReturn(instructor);

        InstructorDTO instructorDTO = instructorService
                                        .addInstructor(
                                                instructor.getName(), instructor.getEmail(), instructor.getPassword()
                                        );

        assertNotNull(instructorDTO);
        assertEquals(instructor.getId(), instructorDTO.getId());
        assertEquals(instructor.getName(), instructorDTO.getName());
        assertEquals(instructor.getEmail(), instructorDTO.getEmail());
        assertEquals(instructor.getPassword(), instructorDTO.getPassword());
        verify(instructorRepository, times(1)).save(any(Instructor.class));


    }

}