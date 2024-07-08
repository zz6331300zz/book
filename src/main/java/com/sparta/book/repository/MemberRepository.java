package com.sparta.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sparta.book.entity.Member;
@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

    @Override
    boolean existsById(String memberId);
    boolean existsByphoneNumber(String phoneNumber);
}
