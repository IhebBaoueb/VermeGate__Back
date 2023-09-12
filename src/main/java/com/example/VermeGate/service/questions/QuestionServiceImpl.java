package com.example.VermeGate.service.questions;

import com.example.VermeGate.dto.AllQuestionResponseDto;
import com.example.VermeGate.dto.QuestionDto;
import com.example.VermeGate.dto.SingleQuestionDto;
import com.example.VermeGate.entities.Questions;
import com.example.VermeGate.entities.User;
import com.example.VermeGate.repository.QuestionRepository;
import com.example.VermeGate.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService{

    public static final int SEARCH_RESULT_PER_PAGE = 5;


    private final UserRepository userRepository ;

    private  final QuestionRepository questionRepository;

    public QuestionServiceImpl(UserRepository userRepository, QuestionRepository questionRepository) {
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public QuestionDto addQuestion(QuestionDto questionDto) {

        Optional<User> optionalUser= userRepository.findById(questionDto.getUserId());
        System.out.println(optionalUser);
       if (optionalUser.isPresent()) {
            Questions question = new Questions();
            question.setTitle(questionDto.getTitle());
            question.setBody(questionDto.getBody());
            question.setTags(questionDto.getTags());
            question.setCreatedDate(new Date());
            question.setUser(optionalUser.get());

            Questions createdQuestion =questionRepository.save(question);
            QuestionDto createdQuestionDto = new QuestionDto();
            createdQuestionDto.setId(createdQuestion.getId());
            createdQuestionDto.setTitle(createdQuestion.getTitle());
           createdQuestionDto.setBody(createdQuestion.getBody());
           createdQuestionDto.setTags(createdQuestion.getTags());
           createdQuestionDto.setCreatedDate(createdQuestion.getCreatedDate());

           return  createdQuestionDto;
       }
        return null;
    }

    @Override
    public AllQuestionResponseDto getAllQuestions(int pageNumber) {
        Pageable paging = PageRequest.of(pageNumber, SEARCH_RESULT_PER_PAGE);
        Page<Questions> questionsPage =questionRepository.findAll(paging);
        AllQuestionResponseDto allQuestionResponseDto = new AllQuestionResponseDto();
        allQuestionResponseDto.setQuestionDtoList(questionsPage.getContent().stream().map(Questions::getQuestionDto).collect(Collectors.toList()));
        allQuestionResponseDto.setPageNumber(questionsPage.getPageable().getPageNumber());
        allQuestionResponseDto.setTotalPages(questionsPage.getTotalPages());

        return allQuestionResponseDto;
    }

    @Override
    public SingleQuestionDto getQuestionById(Long questionId) {
        Optional<Questions> optionalQuestion =  questionRepository.findById(questionId);
        SingleQuestionDto singleQuestionDto = new SingleQuestionDto();
        optionalQuestion.ifPresent(question ->  singleQuestionDto.setQuestionDto(question.getQuestionDto()));
        return singleQuestionDto;

    }


}

