package com.sparta.book.service;

import com.sparta.book.dto.BookRequestDto;
import com.sparta.book.dto.BookResponseDto;
import com.sparta.book.dto.MemberRequestDto;
import com.sparta.book.dto.MemberResponseDto;
import com.sparta.book.entity.Book;
import com.sparta.book.entity.Member;
import com.sparta.book.exception.DuplicateMemberException;
import com.sparta.book.repository.BookRepository;
import com.sparta.book.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberResponseDto createMember(MemberRequestDto requestDto) {
        Member member = new Member(requestDto);
        // DB 저장
        if(memberRepository.existsById(requestDto.getId())){
            throw new DuplicateMemberException("Member with this ID already exists");
        }
        /* 회원 전화번호 중복일 경으 */
        if(memberRepository.existsByphoneNumber(requestDto.getPhoneNumber())){
            throw new DuplicateMemberException("Member with this Phone Number already exists");
        }
        Member saveMember = memberRepository.save(member);
        // Entity -> ResponseDto
        MemberResponseDto memberResponseDto = new MemberResponseDto(member);

        return memberResponseDto;

    }

    public Member getMember(String member_id) {
        return memberRepository.findById(member_id).orElse(null);
    }
}
