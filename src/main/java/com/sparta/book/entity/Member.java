package com.sparta.book.entity;
import com.sparta.book.dto.MemberRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Table(name = "member") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor
public class Member{
    @Id
    @Column(name="id", nullable = false, columnDefinition = "varchar(50)")
    private String id;
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Column(name = "gender", nullable = false, length = 100)
    private String gender;
    @Column(name = "phone_number", unique = true, nullable = false, length = 200)
    private String phoneNumber;
    @Column(name = "address", nullable = false, length = 200)
    private String address;

    public Member(MemberRequestDto requestDto){
        this.id = requestDto.getId();
        this.name = requestDto.getName();
        this.gender = requestDto.getGender();
        this.phoneNumber = requestDto.getPhoneNumber();
        this.address = requestDto.getAddress();
    }
}

