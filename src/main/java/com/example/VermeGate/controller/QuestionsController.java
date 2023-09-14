package com.example.VermeGate.controller;

import com.example.VermeGate.dto.AllQuestionResponseDto;
import com.example.VermeGate.dto.QuestionDto;
import com.example.VermeGate.dto.SingleQuestionDto;
import com.example.VermeGate.service.questions.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8088")
@RequestMapping("/api")
public class QuestionsController {

    private  final QuestionService questionService ;

    public QuestionsController(QuestionService questionService) {
        this.questionService = questionService;
    }


    @PostMapping("/question")
    public ResponseEntity<?> postQuestion(@RequestBody QuestionDto questionDto) {
        QuestionDto createdQuestionDto = questionService.addQuestion(questionDto);
        if (createdQuestionDto == null ) {
            return  new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdQuestionDto);

    }


    @GetMapping("/questions/{pageNumber}")
   public ResponseEntity<AllQuestionResponseDto> getAllQuestions(@PathVariable int pageNumber){
        AllQuestionResponseDto allQuestionResponseDto =questionService.getAllQuestions(pageNumber);
        return  ResponseEntity.ok(allQuestionResponseDto);

   }

   @GetMapping("/question/{questionId}")
   public ResponseEntity<?> getQuestionById(@PathVariable Long questionId) {
           SingleQuestionDto singleQuestionDto = questionService.getQuestionById(questionId);
           if (singleQuestionDto == null) return  ResponseEntity.notFound().build();
           return ResponseEntity.ok(singleQuestionDto);

   }
/*
    @GetMapping("/question/{title}")
    public ResponseEntity<QuestionDto> getQuestionByTitle(@PathVariable String title) {
        QuestionDto questionDto = questionService.getQuestionByTitle(title);
        return ResponseEntity.ok(questionDto );

    } */


}


