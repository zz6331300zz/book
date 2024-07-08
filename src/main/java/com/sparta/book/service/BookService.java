package com.sparta.book.service;

import com.sparta.book.dto.BookRequestDto;
import com.sparta.book.dto.BookResponseDto;
import com.sparta.book.entity.Book;
import com.sparta.book.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookResponseDto createBook(BookRequestDto requestDto) {
        Book book = new Book(requestDto);

        // DB 저장
        Book saveBook = bookRepository.save(book);

        // Entity -> ResponseDto
        BookResponseDto bookResponseDto = new BookResponseDto(book);

        return bookResponseDto;

    }

    public BookResponseDto getBookById(int id) {
        return bookRepository.findAllById(id).stream().map(BookResponseDto::new).findFirst().orElse(null);
    }


    public List<BookResponseDto> getBooks() {

        return bookRepository.findAllByOrderByRegistDateAsc().stream().map(BookResponseDto::new).toList();
    }

    public BookResponseDto createMember(BookRequestDto requestDto) {
        Book book = new Book(requestDto);

        // DB 저장
        Book saveBook = bookRepository.save(book);

        // Entity -> ResponseDto
        BookResponseDto bookResponseDto = new BookResponseDto(book);

        return bookResponseDto;

    }

    public Book getBook(int id){
        return bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("선택한 도서는 존재하지 않습니다."));
    }


}
