package com.sparta.book.controller;


import com.sparta.book.dto.BookRequestDto;
import com.sparta.book.dto.BookResponseDto;
import com.sparta.book.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("books/detail")
    public String detail(@RequestParam(name = "id", required = false, defaultValue = "Guest") String id, Model model) {
        model.addAttribute("id", id);
        return "detail";
    }

    @PostMapping("/books/add")
    @ResponseBody
    public BookResponseDto createBook(@RequestBody BookRequestDto requestDto) {
        // RequestDto -> Entity
        return bookService.createBook(requestDto);

    }

    @GetMapping("/books")
    @ResponseBody
    public List<BookResponseDto> getBooks() {
        // DB 조회
        return bookService.getBooks();
    }

    @GetMapping("/books/detail/{id}")
    @ResponseBody
    public BookResponseDto getBook(@PathVariable int id) {
        // DB 조회
        return bookService.getBookById(id);
    }

}
