package com.farid.attendancesystem.service;

import com.farid.attendancesystem.dto.AttendanceRecordDTO;
import com.farid.attendancesystem.dto.LectureDTO;
import com.farid.attendancesystem.dto.StudentDTO;
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

    private StudentDTO studentDTO;
    private LectureDTO lectureDTO;
    private AttendanceRecord attendanceRecord;
    private AttendanceRecordDTO attendanceRecordDTO;

    private UUID studentId;
    private UUID lectureId;
    private UUID attendanceRecordId;

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

        attendanceRecord = AttendanceRecord.builder().id(UUID.randomUUID()).date(new Date()).status(Status.PRESENT)
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

//    @Test
//    public void testDeleteAttendanceRecord() {
//        when(attendanceRecordRepository.existsById(any(UUID.class))).thenReturn(true);
//        doNothing().when(attendanceRecordRepository).deleteById(any(UUID.class));
//
//        UUID id = UUID.randomUUID();
//        assertDoesNotThrow(() -> attendanceRecordService.deleteAttendanceRecord(id));
//        verify(attendanceRecordRepository, times(1)).deleteById(id);
//    }
//
//    @Test
//    public void testUpdateAttendanceRecord() {
//        when(attendanceRecordRepository.findById(any(UUID.class))).thenReturn(Optional.of(attendanceRecord));
//        when(studentRepository.findById(any(UUID.class))).thenReturn(Optional.of(student));
//        when(lectureRepository.findById(any(UUID.class))).thenReturn(Optional.of(lecture));
//        when(attendanceRecordRepository.save(any(AttendanceRecord.class))).thenReturn(attendanceRecord);
//
//        AttendanceRecordDTO updatedDTO = attendanceRecordDTO;
//        updatedDTO.setStatus(Status.ABSENT);
//
//        AttendanceRecordDTO result = attendanceRecordService.updateAttendanceRecord(attendanceRecord.getId(), updatedDTO);
//
//        assertNotNull(result);
//        assertEquals(attendanceRecord.getId(), result.getId());
//        assertEquals(student.getId(), result.getStudentId());
//        assertEquals(lecture.getId(), result.getLectureId());
//        assertEquals(updatedDTO.getStatus(), result.getStatus());
//    }
//
//    @Test
//    public void testGetAttendanceRecord() {
//        when(attendanceRecordRepository.findById(any(UUID.class))).thenReturn(Optional.of(attendanceRecord));
//
//        AttendanceRecordDTO result = attendanceRecordService.getAttendanceRecord(attendanceRecord.getId());
//
//        assertNotNull(result);
//        assertEquals(attendanceRecord.getId(), result.getId());
//        assertEquals(student.getId(), result.getStudentId());
//        assertEquals(lecture.getId(), result.getLectureId());
//        assertEquals(attendanceRecord.getDate(), result.getDate());
//        assertEquals(attendanceRecord.getStatus(), result.getStatus());
//    }
//
//    @Test
//    public void testGetAllAttendanceRecords() {
//        List<AttendanceRecord> records = Arrays.asList(attendanceRecord);
//        when(attendanceRecordRepository.findAll()).thenReturn(records);
//
//        List<AttendanceRecordDTO> result = attendanceRecordService.getAllAttendanceRecords();
//
//        assertNotNull(result);
//        assertFalse(result.isEmpty());
//        assertEquals(1, result.size());
//        assertEquals(attendanceRecord.getId(), result.get(0).getId());
//    }
}