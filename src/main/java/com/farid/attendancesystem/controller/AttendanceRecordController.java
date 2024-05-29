package com.farid.attendancesystem.controller;

import com.farid.attendancesystem.controller.requestdtos.AttendanceRecordRequestDTO;
import com.farid.attendancesystem.dto.AttendanceRecordDTO;
import com.farid.attendancesystem.service.AttendanceRecordService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/attendance-record")
public class AttendanceRecordController {
    private final AttendanceRecordService attendanceRecordService;

    @GetMapping
    public ResponseEntity<List<AttendanceRecordDTO>> getAttendanceRecords(){
        return new ResponseEntity<>(attendanceRecordService.getAllAttendanceRecords(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendanceRecordDTO> getAttendanceRecord(@PathVariable("id") UUID uuid){
        return new ResponseEntity<>(attendanceRecordService.getAttendanceRecord(uuid), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AttendanceRecordDTO> addAttendanceRecord(
            @RequestBody AttendanceRecordRequestDTO attendanceRecordRequestDTO
    ){
        return new ResponseEntity<>(
                attendanceRecordService.addAttendanceRecord(
                    attendanceRecordRequestDTO.getStudentId(),
                    attendanceRecordRequestDTO.getLectureId(),
                    attendanceRecordRequestDTO.getDate(),
                    attendanceRecordRequestDTO.getStatus()
        ), HttpStatus.OK);
    }

}
