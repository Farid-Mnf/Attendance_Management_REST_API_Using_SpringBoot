package com.farid.attendancesystem.controller;

import com.farid.attendancesystem.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;
@Builder
@Data
@AllArgsConstructor
public class AttendanceRecordRequestDTO {
    private UUID studentId;
    private UUID lectureId;
    private Date date;
    private Status status;
}