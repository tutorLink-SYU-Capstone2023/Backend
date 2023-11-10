package com.capstone.tutorlink.domain.member.command.domain.repository;

import com.capstone.tutorlink.domain.member.command.domain.aggregate.AcceptedTypeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcceptedTypeCategoryRepository extends JpaRepository<AcceptedTypeCategory, String> {
    AcceptedTypeCategory findByField(String field);
}
