package com.sparta.book.controller;


import com.sparta.book.dto.LoanRequestDto;
import com.sparta.book.dto.LoanResponseDto;
import com.sparta.book.entity.Book;
import com.sparta.book.entity.Member;
import com.sparta.book.exception.WrongLoanException;
import com.sparta.book.service.BookService;
import com.sparta.book.service.LoanService;
import com.sparta.book.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class LoanController {

    private final BookService bookService;
    private final MemberService memberService;
    private final LoanService loanService;


    public LoanController(LoanService loanService,MemberService memberService,BookService bookService) {
        this.loanService = loanService;
        this.memberService = memberService;
        this.bookService = bookService;
    }

    @PostMapping("/loans/add")
    @ResponseBody
    public LoanResponseDto createLoan(@RequestBody Map<String,String> param) {
        // RequestDto -> Entity
        String member_id = param.get("member_id");
        int book_id = Integer.valueOf(param.get("book_id"));
        Member member = memberService.getMember(member_id);
        Book book = bookService.getBook(book_id);
        boolean ExistReturn = loanService.existReturnByMemberId(member_id);
            if(book==null){
                throw new WrongLoanException("잘못된 도서 접근입니다.");
            }
            if(member==null){
                throw new WrongLoanException("회원이 존재하지 않습니다.");
            }
            if(!ExistReturn) {
                throw new WrongLoanException("도서 반납이 되지 않았습니다.");
            }

            return loanService.createLoan(new LoanRequestDto(member, book));
    }

    @GetMapping("/books/loans")
    @ResponseBody
    public List<LoanResponseDto> getLoans(@RequestParam("id") String id) {
        // RequestDto -> Entity
        return loanService.getLoans(id);

    }

    @PostMapping("/loans/{id}/return")
    @ResponseBody
    public LoanResponseDto returnBook(@PathVariable("id") Integer id) {
        // RequestDto -> Entity
        return loanService.returnBook(id);
    }

    @GetMapping("/loans/isloan/{id}")
    @ResponseBody
    public String isLoaned(@PathVariable int id) {
        return loanService.isLoanedByBookId(id);
    }
}
