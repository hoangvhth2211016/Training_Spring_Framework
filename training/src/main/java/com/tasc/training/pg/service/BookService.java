package com.tasc.training.pg.service;

import com.tasc.training.pg.model.Book;
import com.tasc.training.pg.model.BookDto;

import java.util.List;

public interface BookService {
    
    List<Book> getAllTemplate();
    
    BookDto getByIdTemplate(Long id);
    
    Book createTemplate(Book book);
    
    void updateTemplate(Long id, BookDto dto);
    
    void deleteTemplate(Long id);
}
