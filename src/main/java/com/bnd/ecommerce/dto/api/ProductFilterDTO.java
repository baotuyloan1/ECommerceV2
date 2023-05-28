package com.bnd.ecommerce.dto.api;

import javax.validation.constraints.Min;

public class ProductFilterDTO {

    private String keyword;

    @Min(value = 1, message = "Page must be higher than 0")
    private int pageNum;

    @Min(value = 1, message = "Page size must higher than 0")
    private int size;
    private String sortField;
    private String sortDir;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortDir() {
        return sortDir;
    }

    public void setSortDir(String sortDir) {
        this.sortDir = sortDir;
    }
}
