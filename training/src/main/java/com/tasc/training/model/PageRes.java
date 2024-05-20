package com.tasc.training.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;

import java.util.List;

@Getter
@Setter
public class PageRes <T> {
    
    private Integer pageNo;
    
    private Integer totalPages;
    
    private Long totalItems;
    
    private List<T> items;

    public PageRes(Page<T> page){
        this.pageNo = page.getNumber();
        this.totalPages = page.getTotalPages();
        this.totalItems = page.getTotalElements();
        this.items = page.getContent();
    }
}
