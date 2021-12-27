package com.yrkim.springsecurity.response;

import lombok.Data;

import java.util.Collection;

@Data
public class ListResult<T> extends CommonResult {
    private Collection<T> listData;
    private long totalPages;
    private long nowPage;
    private long totalElements;
    private long pageLimit;
}
