package com.tasc.training.pg.service;

import com.tasc.training.exception.NotFoundException;
import com.tasc.training.pg.model.Book;
import com.tasc.training.pg.model.BookDto;
import com.tasc.training.pg.model.BookDtoMapper;
import com.tasc.training.pg.model.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{
    
    @Autowired
    @Qualifier("PGJdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    @Qualifier("PGNamedJdbcTemplate")
    private NamedParameterJdbcTemplate namedJdbcTemplate;
    
    @Override
    public List<Book> getAllTemplate() {
        String query = "select * from books";
        return jdbcTemplate.query(query, new BookMapper());
    }
    
    @Override
    public BookDto getByIdTemplate(Long id) {
        String query = "select id, isbn, title, description, total_page, publish_date " +
                "from books " +
                "where id = :id";
        return namedJdbcTemplate
                .query(query,new MapSqlParameterSource("id", id), new BookDtoMapper())
                .stream()
                .findFirst()
                .orElseThrow(()-> new NotFoundException("Book Not Found"));
    }
    
    @Override
    public Book createTemplate(Book book) {
        String query = "insert into books (id, isbn, title, description, total_page, publish_date, created_at, updated_at) " +
                "values (:id, :isbn, :title, :description, :totalPage, :publishDate, :createdAt, :updatedAt)";
        BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(book);
        namedJdbcTemplate.update(query, paramSource);
        return book;
    }
    
    @Override
    public void updateTemplate(Long id, BookDto dto) {
        String query = "update books " +
                "set isbn = :isbn, title = :title, description = :description, publish_date = :publishDate, total_page = :totalPage, updated_at = :updatedAt " +
                "where id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("isbn", dto.getIsbn())
                .addValue("title", dto.getTitle())
                .addValue("description", dto.getDescription())
                .addValue("totalPage", dto.getTotalPage())
                .addValue("publishDate", dto.getPublishDate())
                .addValue("updatedAt", LocalDateTime.now())
                .addValue("id", id);
        namedJdbcTemplate.update(query, params);
    }
    
    @Override
    public void deleteTemplate(Long id) {
        String query = "delete from books where id = ?";
        jdbcTemplate.update(query, id);
    }
}
