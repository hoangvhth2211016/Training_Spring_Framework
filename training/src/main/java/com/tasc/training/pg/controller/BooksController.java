package com.tasc.training.pg.controller;

import com.tasc.training.pg.model.Book;
import com.tasc.training.pg.model.BookDto;
import com.tasc.training.pg.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("books")
public class BooksController {
    
    @Autowired
    private BookService bookService;
    
    @GetMapping
    public List<Book> getAll(){
        return bookService.getAllTemplate();
    }
    
    @GetMapping("{id}")
    public BookDto getById(@PathVariable Long id){
        return bookService.getByIdTemplate(id);
    }
    
    @PostMapping
    public Book create(@RequestBody BookDto dto){
        Book book = Book.builder()
                .id(dto.getId())
                .isbn(dto.getIsbn())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .totalPage(dto.getTotalPage())
                .publishDate(dto.getPublishDate())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        return bookService.createTemplate(book);
    }
    
    @PutMapping("{id}")
    public String update(@PathVariable Long id, @RequestBody BookDto dto){
        bookService.updateTemplate(id, dto);
        return "book updated";
    }
    
    @DeleteMapping("{id}")
    public String delete(@PathVariable Long id){
        bookService.deleteTemplate(id);
        return "book deleted";
    }
}
