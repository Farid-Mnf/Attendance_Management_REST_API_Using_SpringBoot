package com.farid.attendancesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LectureDTO {
    private UUID id;
    private String title;
    private CourseDTO courseDTO;
    private LocalDateTime dateTime;
    private List<AttendanceRecordDTO> attendanceRecordDTOS;
}
