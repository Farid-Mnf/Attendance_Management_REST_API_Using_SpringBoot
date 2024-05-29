package com.farid.attendancesystem.service;

import com.farid.attendancesystem.dto.LectureDTO;
import com.farid.attendancesystem.entity.Course;
import com.farid.attendancesystem.entity.Lecture;
import com.farid.attendancesystem.repository.CourseRepository;
import com.farid.attendancesystem.repository.LectureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LectureServiceTest {
    @Mock
    private LectureRepository lectureRepository;
    @Mock
    private CourseRepository courseRepository;
    @InjectMocks
    private LectureService lectureService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(LectureServiceTest.class);
    }

    @Test
    void testGetLecture(){
        UUID uuid = UUID.randomUUID();
        Lecture lecture = Lecture.builder().id(uuid).title("networking").course(Course.builder().name("IT").build()).build();
        when(lectureRepository.findById(any(UUID.class))).thenReturn(Optional.of(lecture));

        LectureDTO lectureDTO = lectureService.getLecture(uuid);

        assertEquals(lecture.getId(), lectureDTO.getId());
        assertEquals(lecture.getTitle(), lectureDTO.getTitle());
        assertEquals(lecture.getCourse().getName(), lectureDTO.getCourseDTO().getName());
    }

    @Test
    void testAddLecture(){
        UUID uuid = UUID.randomUUID();
        UUID courseUuid = UUID.randomUUID();
        LocalDateTime localDateTime = LocalDateTime.now();

        Course course = Course.builder().id(courseUuid).name("CS50").description("learn computer science topics").build();
        Lecture lecture = Lecture.builder().id(uuid).title("intro to databases").dateTime(localDateTime).course(course).build();

        when(lectureRepository.save(any(Lecture.class))).thenReturn(lecture);
        when(courseRepository.findById(any(UUID.class))).thenReturn(Optional.of(course));

        LectureDTO lectureDTO = lectureService.addLecture(lecture.getTitle(), course.getId());

        assertEquals(lecture.getId(), lectureDTO.getId());
        assertEquals(lecture.getTitle(), lectureDTO.getTitle());
        assertEquals(lecture.getDateTime(), lectureDTO.getDateTime());
        assertEquals(lecture.getCourse().getName(), lectureDTO.getCourseDTO().getName());
        assertEquals(lecture.getCourse().getDescription(), lectureDTO.getCourseDTO().getDescription());
        assertEquals(lecture.getCourse().getId(), lectureDTO.getCourseDTO().getId());

    }
}