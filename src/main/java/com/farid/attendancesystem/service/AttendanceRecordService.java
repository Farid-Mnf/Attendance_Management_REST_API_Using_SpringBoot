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
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AttendanceRecordService {
    private final AttendanceRecordRepository attendanceRecordRepository;
    private final StudentRepository studentRepository;
    private final LectureRepository lectureRepository;


    public AttendanceRecordDTO addAttendanceRecord(
            UUID studentId, UUID lectureId, Date date, Status status) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new RuntimeException("Lecture not found with id: " + lectureId));

        AttendanceRecord attendanceRecord = AttendanceRecord.builder()
                .student(student)
                .lecture(lecture)
                .date(date)
                .status(status)
                .build();

        attendanceRecord = attendanceRecordRepository.save(attendanceRecord);

        return mapToDTO(attendanceRecord);
    }

    public void deleteAttendanceRecord(UUID id) {
        if (attendanceRecordRepository.existsById(id)) {
            attendanceRecordRepository.deleteById(id);
        } else {
            throw new RuntimeException("AttendanceRecord not found with id: " + id);
        }
    }

    public AttendanceRecordDTO updateAttendanceRecord(
            UUID id, AttendanceRecordDTO updatedAttendanceRecordDTO
    )
    {
        AttendanceRecord attendanceRecord = attendanceRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AttendanceRecord not found with id: " + id));

        Student student = studentRepository.findById(updatedAttendanceRecordDTO.getStudentDTO().getId())
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + updatedAttendanceRecordDTO.getStudentDTO().getId()));

        Lecture lecture = lectureRepository.findById(updatedAttendanceRecordDTO.getLectureDTO().getId())
                .orElseThrow(() -> new RuntimeException("Lecture not found with id: " + updatedAttendanceRecordDTO.getLectureDTO().getId()));

        attendanceRecord.setStudent(student);
        attendanceRecord.setLecture(lecture);
        attendanceRecord.setDate(updatedAttendanceRecordDTO.getDate());
        attendanceRecord.setStatus(updatedAttendanceRecordDTO.getStatus());

        attendanceRecord = attendanceRecordRepository.save(attendanceRecord);

        return mapToDTO(attendanceRecord);
    }

    public AttendanceRecordDTO getAttendanceRecord(UUID id) {
        AttendanceRecord attendanceRecord = attendanceRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AttendanceRecord not found with id: " + id));

        return mapToDTO(attendanceRecord);
    }

    public List<AttendanceRecordDTO> getAllAttendanceRecords() {
        return attendanceRecordRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private AttendanceRecordDTO mapToDTO(AttendanceRecord attendanceRecord) {
        StudentDTO studentDTO = StudentDTO.builder().id(attendanceRecord.getStudent().getId())
                .name(attendanceRecord.getStudent().getName())
                .email(attendanceRecord.getStudent().getEmail()).build();

        LectureDTO lectureDTO = LectureDTO.builder()
                .id(attendanceRecord.getLecture().getId())
                .title(attendanceRecord.getLecture().getTitle())
                .dateTime(attendanceRecord.getLecture().getDateTime()).build();

        return AttendanceRecordDTO.builder()
                .id(attendanceRecord.getId())
                .studentDTO(studentDTO)
                .lectureDTO(lectureDTO)
                .date(attendanceRecord.getDate())
                .status(attendanceRecord.getStatus())
                .build();
    }

}
