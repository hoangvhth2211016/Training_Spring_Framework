package com.tasc.training.pg.model;

import com.tasc.training.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Book extends BaseEntity {
    
    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;
    
    @Column(name = "title", nullable = false)
    private String title;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "total_page", nullable = false)
    private Integer totalPage;
    
    @Column(name = "publish_date", nullable = false)
    private LocalDate publishDate;
    
}