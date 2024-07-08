package com.sparta.book.dto;

import com.sparta.book.entity.Book;
import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
public class BookResponseDto {

    int id;
    String title;
    String author;
    String language;
    String publisher;
    LocalDateTime regist_date;

    public BookResponseDto(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.language = book.getLanguage();
        this.publisher = book.getPublisher();
        this.regist_date = book.getRegistDate();
    }

    public BookResponseDto(int id, String title, String author, String language, String publisher, LocalDateTime regist_date) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.language = language;
        this.publisher = publisher;
        this.regist_date = regist_date;
    }
}
