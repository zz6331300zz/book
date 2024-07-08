package com.sparta.book.dto;

import com.sparta.book.entity.Loan;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class LoanResponseDto {

    int id;
    int bookID;
    String bookTitle;
    String bookAuthor;
    String memberID;
    String memberName;
    String memberPhoneNumber;
    LocalDateTime loanDate;
    LocalDateTime returnDate;

    public LoanResponseDto(Loan loan) {
        this.id = loan.getId();
        this.bookID = loan.getBook().getId();
        this.bookTitle = loan.getBook().getTitle();
        this.bookAuthor = loan.getBook().getAuthor();
        this.memberID = loan.getMember().getId();
        this.memberName = loan.getMember().getName();
        this.memberPhoneNumber = loan.getMember().getPhoneNumber();
        this.loanDate = loan.getLoanDate();
        this.returnDate = loan.getReturnDate();
    }
}
