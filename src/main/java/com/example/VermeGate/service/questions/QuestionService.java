package com.example.VermeGate.service.questions;

//import com.example.VermeGate.dto.AllInformationResponseDto;
import com.example.VermeGate.dto.AllQuestionResponseDto;
import com.example.VermeGate.dto.QuestionDto;
import com.example.VermeGate.dto.SingleQuestionDto;

public interface QuestionService {
    QuestionDto addQuestion(QuestionDto questionDto);

    AllQuestionResponseDto getAllQuestions(int pageNumber);

    SingleQuestionDto getQuestionById(Long questionId);


}
