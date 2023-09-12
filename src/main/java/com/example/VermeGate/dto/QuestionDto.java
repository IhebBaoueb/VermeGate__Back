package com.example.VermeGate.dto;

import lombok.Data;

import java.util.Date;

@Data
public class QuestionDto {
    private Long id ;

    private String title;

    private  String body ;

    private String tags;

    private Date createdDate;

    private Long userId ;

    private String username;
}
