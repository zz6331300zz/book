package com.sparta.book.dto;

import com.sparta.book.entity.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {

    private String id;
    private String name;
    private String gender;
    private String phoneNumber;
    private String address;

    public MemberResponseDto(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.gender = member.getGender();
        this.phoneNumber = member.getPhoneNumber();
        this.address = member.getAddress();
    }
}
