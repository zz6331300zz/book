package com.sparta.book.dto;

import com.sparta.book.entity.Book;
import com.sparta.book.entity.Member;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class LoanRequestDto {
    int id;
    Book book;
    Member member;
    LocalDateTime loan_date;
    LocalDateTime return_date;

    public LoanRequestDto(Member member,Book book){
        this.book = book;
        this.member = member;
    }
}
