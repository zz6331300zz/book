package com.sparta.book.service;

import com.sparta.book.dto.LoanRequestDto;
import com.sparta.book.dto.LoanResponseDto;
import com.sparta.book.entity.Loan;
import com.sparta.book.entity.Member;
import com.sparta.book.repository.LoanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public LoanResponseDto createLoan(LoanRequestDto requestDto) {
        Loan loan = new Loan(requestDto);
        // DB 저장
        Loan saveLoan = loanRepository.save(loan);

        // Entity -> ResponseDto
        LoanResponseDto loanResponseDto = new LoanResponseDto(loan);

        return loanResponseDto;

    }

    public List<LoanResponseDto> getLoans(String id) {
        if (id == null || id.isEmpty()) {
            return loanRepository.findAllByOrderByLoanDateAsc().stream().map(LoanResponseDto::new).toList();
        }
        return loanRepository.findByMemberIdOrderByLoanDateAsc(id).stream().map(LoanResponseDto::new).toList();
    }
    public boolean existReturnByMemberId(String memberId){
        Loan loan = loanRepository.findTopByMemberIdOrderByLoanDateDesc(memberId).orElse(null);
        if(loan!=null){
            // 회원이 도서 대출 내역이 있고 반납내역이 존재할때 : 대출가능
            if(loan.getReturnDate()!=null){
                return true;
            }else {
                return false;//반납내역이 존재하지 않을때
            }
        }else{
            return true; //회원이 도서 대출 내역이 없을때는 대출 가능.
        }
    }
    public String isLoanedByBookId(int bookId) {
        Loan loan = loanRepository.findTopByBookIdOrderByLoanDateDesc(bookId).orElse(null);
        //조회 결과 도서 대출 내역이 있을때.
        if(loan!=null){
            // 도서 대출 내역이 있고 반납내역이 존재할때
            if(loan.getReturnDate()!=null){
                return "대출 가능";
            }else {
                return "대출 불가능";//반납내역이 존재하지 않을때
            }
        }else{
            return "대출 가능"; //도서 대출 내역이 없을때는 대출 가능.
        }
    }


//    public String isLoaned(int id) {
//        int count = loanRepository.loanedFindByID(id);
//        if (count > 0) {
//            return "대출 불가능";
//        } else {
//            return "대출 가능";
//        }
//    }

    @Transactional
    public LoanResponseDto returnBook(int id) {
        Optional<Loan> loanOptional = loanRepository.findTopByBookIdOrderByLoanDateDesc(id);
        // DB 저장
//        System.out.println(loan_id);
        if (loanOptional.isPresent()) {
            Loan loan = loanOptional.get();
            loan.update();
            return new LoanResponseDto(loan);
        }return null;
    }
}
