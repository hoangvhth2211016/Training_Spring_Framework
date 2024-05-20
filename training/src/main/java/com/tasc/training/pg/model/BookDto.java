package com.tasc.training.pg.model;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    
    private Long id;
    
    private String isbn;
    
    private String title;
    
    private String description;
    
    private Integer totalPage;
    
    private LocalDate publishDate;
    
}
