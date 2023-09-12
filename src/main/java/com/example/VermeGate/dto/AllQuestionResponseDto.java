package com.example.VermeGate.dto;

import lombok.Data;

import java.util.List;

@Data
public class AllQuestionResponseDto {

    private List<QuestionDto> questionDtoList ;

    private Integer totalPages;

    private  Integer pageNumber;

}
