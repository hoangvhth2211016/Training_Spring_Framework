package com.tasc.training.pg.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDtoMapper implements RowMapper<BookDto> {
    
    @Override
    public BookDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return BookDto.builder()
                .id(rs.getLong("id"))
                .isbn(rs.getString("isbn"))
                .title(rs.getString("title"))
                .description(rs.getString("description"))
                .publishDate(rs.getDate("publish_date").toLocalDate())
                .totalPage(rs.getInt("total_page"))
                .build();
    }
}
