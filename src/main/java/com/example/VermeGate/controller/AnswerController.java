package com.example.VermeGate.controller;

import com.example.VermeGate.dto.AnswerDto;
import com.example.VermeGate.service.answer.AnswerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {

    private  final AnswerService answerService;

    public  AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping
    public ResponseEntity<?> addAnswer(@RequestBody AnswerDto answerDto) {
      AnswerDto createdAnswerDto=  answerService.postAnswer(answerDto);
      if (createdAnswerDto == null)
          return  new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
      return  ResponseEntity.status(HttpStatus.CREATED).body(createdAnswerDto);
    }

}
