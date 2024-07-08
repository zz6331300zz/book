package com.sparta.book.entity;

import com.sparta.book.dto.LoanRequestDto;
import com.sparta.book.entity.Book;
import com.sparta.book.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

/*
이노베이션 캠프 LV-2 : 대출 Entity
 */

@Getter
@AllArgsConstructor
@Builder
@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false, columnDefinition = "int")
    private int id;

    @ManyToOne
    @JoinColumn(name="book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name="member_id", nullable = false)
    private Member member;

    @CreationTimestamp
    @Column(name="loanDate")
    private LocalDateTime loanDate;

    @Column(name="returnDate")
    private LocalDateTime returnDate;

    public Loan() {
    }

    public Loan(LoanRequestDto requestDto) {
        this.id = requestDto.getId();
        this.book = requestDto.getBook();
        this.member = requestDto.getMember();
        this.loanDate = requestDto.getLoan_date();
        this.returnDate = requestDto.getReturn_date();
    }

    public void update() {
        this.returnDate = LocalDateTime.now();
    }

}
