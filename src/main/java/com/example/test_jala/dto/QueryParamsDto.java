package com.example.test_jala.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class QueryParamsDto {
    private Integer page;
    private Integer pageSize;
    private String filter;
    private String sort;
    private String direction;
    private Date afterDate;
    private Date beforeDate;
}
