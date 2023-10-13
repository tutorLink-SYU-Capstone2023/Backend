package com.ohgiraffers.tutorlinktest.member.respository;

import com.ohgiraffers.tutorlinktest.member.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
    Authority findByAuthorityName(String authorityName);

}
