package com.farid.attendancesystem.service;

import com.farid.attendancesystem.dto.*;
import com.farid.attendancesystem.entity.AttendanceRecord;
import com.farid.attendancesystem.entity.Enrollment;
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
        }else{
            throw new RuntimeException("can't delete student");
        }
    }

    public StudentDTO updateStudent(UUID id, StudentDTO updatedStudent){
        if(studentRepository.existsById(id)){
            Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("student not found with id: " + id));
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
        Student student;
        student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("student not found with id: " + id));
        return StudentDTO.builder()
                .id(student.getId())
                .name(student.getName())
                .email(student.getEmail())
                .attendanceRecordDTOS(getAttendanceRecordsDTOS(student))
                .enrollmentDTOS(getEnrollmentsDTOS(student))
                .build();
    }

    public List<EnrollmentDTO> getEnrollmentsDTOS(Student student){
        List<EnrollmentDTO> enrollmentDTOS = new ArrayList<>();

        if(student.getEnrollments() != null)
            for(Enrollment enrollment: student.getEnrollments()){
                enrollmentDTOS.add(
                        EnrollmentDTO.builder()
                                .id(enrollment.getId())
                                .courseDTO(CourseDTO.builder()
                                        .id(enrollment.getCourse().getId())
                                        .name(enrollment.getCourse().getName())
                                        .build())
                                .studentDTO(StudentDTO.builder()
                                        .id(enrollment.getStudent().getId())
                                        .name(enrollment.getStudent().getName()).build()).build()
                );
            }

        return enrollmentDTOS;
    }

    public List<AttendanceRecordDTO> getAttendanceRecordsDTOS(Student student){
        List<AttendanceRecordDTO> attendanceRecordDTOS = new ArrayList<>();

        if(student.getAttendanceRecords() != null)
            for(AttendanceRecord attendanceRecord: student.getAttendanceRecords()){
                attendanceRecordDTOS.add(
                        AttendanceRecordDTO.builder().id(attendanceRecord.getId())
                                .date(attendanceRecord.getDate())
                                .status(attendanceRecord.getStatus())
                                .lectureDTO(
                                        LectureDTO.builder()
                                                .title(attendanceRecord.getLecture().getTitle())
                                                .dateTime(attendanceRecord.getLecture().getDateTime())
                                                .id(attendanceRecord.getLecture().getId()).build()
                                )
                                .studentDTO(
                                        StudentDTO.builder()
                                                .id(attendanceRecord.getStudent().getId())
                                                .name(attendanceRecord.getStudent().getName())
                                                .build()

                                )
                                .build()
                );
            }

        return attendanceRecordDTOS;
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
