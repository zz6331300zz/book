package com.sparta.book.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BookRequestDto {

    Long bookID;
    String title;
    String author;
    String language;
    String publisher;
    LocalDateTime regist_date;

}
