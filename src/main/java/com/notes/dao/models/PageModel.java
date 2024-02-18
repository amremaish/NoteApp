package com.notes.dao.models;

import org.springframework.data.domain.Page;

import java.util.List;

public class PageModel<T> {

    private List<T> content;

    private int pageSize, pageNumber;
    private long totalElements;


    public PageModel(Page<T> page) {
        this.content = page.getContent();
        this.pageSize = page.getSize();
        this.pageNumber = page.getNumber();
        this.totalElements = page.getTotalElements();
    }

    public PageModel() {

    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }
}
