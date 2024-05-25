package com.farid.attendancesystem.repository;

import com.farid.attendancesystem.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InstructorRepository extends JpaRepository<Instructor, UUID> {
}
