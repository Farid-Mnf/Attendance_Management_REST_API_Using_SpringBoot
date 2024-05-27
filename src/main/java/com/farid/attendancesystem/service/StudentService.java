package com.farid.attendancesystem.service;

import com.farid.attendancesystem.dto.StudentDTO;
import com.farid.attendancesystem.entity.Student;
import com.farid.attendancesystem.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentDTO addStudent(String name, String email){
        Student student = Student.builder().name(name).email(email).build();
        student = studentRepository.save(student);
        return StudentDTO.builder().id(student.getId())
                .name(student.getName())
                .email(student.getEmail()).build();
    }

    public void deleteStudent(UUID id){
        if(studentRepository.existsById(id)){
            studentRepository.deleteById(id);
        }
    }

    public StudentDTO updateStudent(UUID id, StudentDTO updatedStudent){
        if(studentRepository.existsById(id)){
            Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("course not found with id: " + id));
            student.setName(updatedStudent.getName());
            student.setEmail(updatedStudent.getEmail());
            student = studentRepository.save(student);
            return StudentDTO.builder()
                    .id(student.getId())
                    .name(student.getName())
                    .email(student.getEmail())
                    .build();
        }
        return null;
    }

    public StudentDTO getStudent(UUID id){
        Student student = null;
        student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("course not found with id: " + id));
        return StudentDTO.builder()
                .id(student.getId())
                .name(student.getName())
                .email(student.getEmail())
                .build();
    }

    public List<StudentDTO> getAllStudents() {
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for(Student student: studentRepository.findAll()){
            studentDTOS.add(StudentDTO.builder().id(student.getId()).name(student.getName())
                            .email(student.getEmail())
                    .build());
        }
        return studentDTOS;
    }
}
