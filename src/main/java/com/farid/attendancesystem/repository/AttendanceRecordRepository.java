package com.farid.attendancesystem.repository;

import com.farid.attendancesystem.entity.AttendanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, UUID> {
}
