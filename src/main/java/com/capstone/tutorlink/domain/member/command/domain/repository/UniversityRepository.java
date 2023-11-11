package com.capstone.tutorlink.domain.member.command.domain.repository;

import com.capstone.tutorlink.domain.member.command.domain.aggregate.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends JpaRepository<University, String> {
    University findByUnivName(String univName);
}
