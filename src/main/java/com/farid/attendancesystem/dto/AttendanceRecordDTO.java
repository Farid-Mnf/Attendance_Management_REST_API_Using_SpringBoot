package com.farid.attendancesystem.dto;

import com.farid.attendancesystem.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceRecordDTO {
    private UUID id;
    private StudentDTO studentDTO;
    private LectureDTO lectureDTO;
    private Date date;
    private Status status;
}
