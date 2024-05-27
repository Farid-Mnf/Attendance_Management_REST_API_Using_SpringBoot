package com.farid.attendancesystem.service;

import com.farid.attendancesystem.dto.AttendanceRecordDTO;
import com.farid.attendancesystem.entity.AttendanceRecord;
import com.farid.attendancesystem.entity.Lecture;
import com.farid.attendancesystem.entity.Status;
import com.farid.attendancesystem.entity.Student;
import com.farid.attendancesystem.repository.AttendanceRecordRepository;
import com.farid.attendancesystem.repository.LectureRepository;
import com.farid.attendancesystem.repository.StudentRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AttendanceRecordServiceTest {
    @Mock
    private AttendanceRecordRepository attendanceRecordRepository;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private LectureRepository lectureRepository;
    @InjectMocks
    private AttendanceRecordService attendanceRecordService;

    @BeforeAll
    public static void setUp() {
        MockitoAnnotations.openMocks(AttendanceRecordServiceTest.class);
    }

    @Test
    public void findStudent(){
        UUID id = UUID.randomUUID();
       Student student1 = Student.builder().id(id).name("farid")
               .email("farid@gmail.com").build();
        when(studentRepository.findById(any(UUID.class))).thenReturn(Optional.of(student1));
       Student result = studentRepository.findById(id).orElseThrow();
       assertEquals(result.getName(), student1.getName());
       assertEquals(result.getId(), student1.getId());
    }
    @Test
    public void testAddAttendanceRecord() {
        UUID studentId = UUID.randomUUID();
        Student student = Student.builder().id(studentId).build();

        UUID lectureId = UUID.randomUUID();
        Lecture lecture = Lecture.builder().id(lectureId).build();

        AttendanceRecord attendanceRecord = AttendanceRecord.builder().id(UUID.randomUUID()).date(new Date()).status(Status.PRESENT)
                .lecture(lecture).student(student).build();

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(lectureRepository.findById(lectureId)).thenReturn(Optional.of(lecture));
        when(attendanceRecordRepository.save(any(AttendanceRecord.class))).thenReturn(attendanceRecord);


        AttendanceRecordDTO result = attendanceRecordService.addAttendanceRecord(
                student.getId(),
                lecture.getId(),
                new Date(),
                Status.PRESENT
        );

        assertNotNull(result);
        assertEquals(attendanceRecord.getId(), result.getId());
        assertEquals(student.getId(), result.getStudentDTO().getId());
        assertEquals(lecture.getId(), result.getLectureDTO().getId());
        assertEquals(attendanceRecord.getDate(), result.getDate());
        assertEquals(attendanceRecord.getStatus(), result.getStatus());
    }
}