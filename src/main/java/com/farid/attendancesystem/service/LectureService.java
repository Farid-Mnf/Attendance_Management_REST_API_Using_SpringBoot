package com.farid.attendancesystem.service;

import com.farid.attendancesystem.dto.CourseDTO;
import com.farid.attendancesystem.dto.LectureDTO;
import com.farid.attendancesystem.entity.Lecture;
import com.farid.attendancesystem.repository.CourseRepository;
import com.farid.attendancesystem.repository.LectureRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LectureService {
    private final LectureRepository lectureRepository;
    private final CourseRepository courseRepository;
    public LectureDTO addLecture(String title, UUID courseID){
        Lecture lecture = lectureRepository.save(Lecture.builder()
                        .title(title)
                        .dateTime(LocalDateTime.now())
                        .course(courseRepository.findById(courseID).orElseThrow())
                .build());
        return mapToLectureDTO(lecture);
    }
    public LectureDTO getLecture(UUID id){
        return mapToLectureDTO(lectureRepository.findById(id).orElseThrow());
    }
    public void deleteLecture(UUID uuid){
        lectureRepository.deleteById(uuid);
    }


    public List<LectureDTO> getLectures() {
        List<LectureDTO> lectureDTOS = new ArrayList<>();
        for(Lecture lecture: lectureRepository.findAll()){
            lectureDTOS.add(mapToLectureDTO(lecture));
        }
        return lectureDTOS;

    }
    public LectureDTO mapToLectureDTO(Lecture lecture){
        return LectureDTO.builder()
                .id(lecture.getId())
                .title(lecture.getTitle())
                .courseDTO(CourseDTO.builder()
                        .id(lecture.getCourse().getId())
                        .name(lecture.getCourse().getName())
                        .description(lecture.getCourse().getDescription()).build())
                .dateTime(lecture.getDateTime())
                .build();
    }

}
