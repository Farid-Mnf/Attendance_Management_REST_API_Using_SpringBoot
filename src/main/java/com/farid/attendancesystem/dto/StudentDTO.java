package com.farid.attendancesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDTO {
    private UUID id;
    private String name;
    private String email;
    private List<AttendanceRecordDTO> attendanceRecordDTOS;
    private List<EnrollmentDTO> enrollmentDTOS;
}
