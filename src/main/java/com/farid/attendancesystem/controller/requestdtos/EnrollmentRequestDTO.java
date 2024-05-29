package com.farid.attendancesystem.controller.requestdtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;
@Builder
@Data
@AllArgsConstructor
public class EnrollmentRequestDTO {
    private UUID courseId;
    private UUID studentId;
}
