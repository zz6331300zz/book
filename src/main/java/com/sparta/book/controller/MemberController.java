package com.sparta.book.controller;


import com.sparta.book.dto.BookRequestDto;
import com.sparta.book.dto.BookResponseDto;
import com.sparta.book.dto.MemberRequestDto;
import com.sparta.book.dto.MemberResponseDto;
import com.sparta.book.service.BookService;
import com.sparta.book.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/books/signup")
    @ResponseBody
    public MemberResponseDto createMember(@RequestBody MemberRequestDto requestDto) {
        // RequestDto -> Entity
        return memberService.createMember(requestDto);

    }

}
