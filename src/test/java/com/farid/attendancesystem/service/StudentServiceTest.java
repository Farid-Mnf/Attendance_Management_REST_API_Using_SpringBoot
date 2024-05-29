package com.farid.attendancesystem.service;

import com.farid.attendancesystem.dto.StudentDTO;
import com.farid.attendancesystem.entity.Student;
import com.farid.attendancesystem.repository.StudentRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(StudentServiceTest.class);
    }

    @Test
    void addStudent() {
        UUID student1UUID = UUID.randomUUID();
        Student student1 = new Student(student1UUID, "Farid", "farid@gmail.com", null, null);
        when(studentRepository.save(any(Student.class))).thenReturn(student1);
        // do logic
        StudentDTO studentDTO = studentService.addStudent(student1.getName(), student1.getEmail());
        // test
        assertEquals(student1.getName(), studentDTO.getName());
        assertEquals(student1.getId(), studentDTO.getId());
        assertEquals(student1.getEmail(), studentDTO.getEmail());
    }

    @Test
    void deleteStudent() {
        UUID idToDelete = UUID.randomUUID();
        when(studentRepository.existsById(idToDelete)).thenReturn(true);

        // Act
        studentService.deleteStudent(idToDelete);

        // Assert
        verify(studentRepository, times(1)).existsById(idToDelete);
        verify(studentRepository, times(1)).deleteById(idToDelete);
    }

    @Test
    public void testDeleteStudent_NotExists() {
        // Arrange
        UUID idToDelete = UUID.randomUUID();
        when(studentRepository.existsById(idToDelete)).thenReturn(false);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> studentService.deleteStudent(idToDelete));

        verify(studentRepository, times(1)).existsById(idToDelete);
        verify(studentRepository, times(0)).deleteById(idToDelete);
    }

    @Test
    void updateStudent() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        Student student = Student
                                    .builder()
                                    .id(uuid)
                                    .name("Farid")
                                    .email("farid@gmail.com")
                                    .build();
        StudentDTO studentDTO = StudentDTO.builder()
                        .name(student.getName()).email(student.getEmail()).id(uuid).build();

        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.existsById(any(UUID.class))).thenReturn(true);
        when(studentRepository.findById(any(UUID.class))).thenReturn(Optional.of(student));
        // Act
        StudentDTO result = studentService.updateStudent(uuid, studentDTO);
        // Assert
        assertEquals(studentDTO.getName(), result.getName());
        assertEquals(studentDTO.getEmail(), result.getEmail());
        assertEquals(studentDTO.getId(), result.getId());


        verify(studentRepository, times(1)).existsById(uuid);
        verify(studentRepository, times(1)).existsById(uuid);
        verify(studentRepository, times(1)).save(student);

    }

    @Test
    void getStudent() {
        UUID uuid = UUID.randomUUID();
        Student student = Student.builder().id(uuid).name("farid").email("farid@gmail.com").build();

        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));

        StudentDTO result = studentService.getStudent(uuid);

        assertEquals(student.getId(), result.getId());
        assertEquals(student.getEmail(), result.getEmail());
        assertEquals(student.getName(), result.getName());
        assertNotNull(result);
    }

    @Test
    void getNonExistingStudent(){
        UUID uuid = UUID.randomUUID();
        doThrow(new RuntimeException("course not found with id: " + uuid)).when(studentRepository).findById(uuid);

        Exception exception = assertThrows(RuntimeException.class, () -> studentService.getStudent(uuid));
        assertEquals("course not found with id: " + uuid, exception.getMessage());

    }

    @Test
    void getAllStudents() {

        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();

        Student student1 = Student.builder().id(uuid1).name("Farid").build();
        Student student2 = Student.builder().id(uuid2).name("Ahmed").build();

        when(studentRepository.findAll()).thenReturn(List.of(student1, student2));

        // act
        List<StudentDTO> studentDTOS = studentService.getAllStudents();

        // Assert
        assertEquals(2, studentDTOS.size());
        assertEquals(student1.getId(), studentDTOS.get(0).getId());
        assertEquals(student1.getName(), studentDTOS.get(0).getName());

        assertEquals(student2.getId(), studentDTOS.get(1).getId());
        assertEquals(student2.getName(), studentDTOS.get(1).getName());



    }
}