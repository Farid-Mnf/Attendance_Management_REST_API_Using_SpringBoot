package com.farid.attendancesystem.repository;

import com.farid.attendancesystem.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface InstructorRepository extends JpaRepository<Instructor, UUID> {
    Optional<Instructor> findInstructorByEmail(String email);
}
