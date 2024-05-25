package com.farid.attendancesystem.repository;

import com.farid.attendancesystem.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LectureRepository extends JpaRepository<Lecture, UUID> {
}
