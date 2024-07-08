package com.sparta.book.repository;

import com.sparta.book.dto.LoanResponseDto;
import com.sparta.book.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {


    List<Loan> findAllByOrderByLoanDateAsc();

    List<Loan> findByMemberIdOrderByLoanDateAsc(String id);

    Optional<Loan> findTopByBookIdOrderByLoanDateDesc(int bookId);

    Optional<Loan> findTopByMemberIdOrderByLoanDateDesc(String memberId);
}



