package com.farid.attendancesystem.controller;

import com.farid.attendancesystem.dto.LectureDTO;
import com.farid.attendancesystem.service.LectureService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/lecture")
public class LectureController {
    private final LectureService lectureService;

    @GetMapping("/{id}")
    public ResponseEntity<LectureDTO> getLecture(@PathVariable("id")UUID uuid){
        return new ResponseEntity<>(lectureService.getLecture(uuid), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LectureDTO> addLecture(@RequestBody LectureRequestDTO lectureRequestDTO){
        LectureDTO lectureDTO = lectureService.addLecture(
                lectureRequestDTO.getTitle(),
                lectureRequestDTO.getCourseId()
        );
        return new ResponseEntity<>(lectureDTO, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteLecture(@PathVariable("id") UUID uuid){
        try{
            lectureService.deleteLecture(uuid);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
