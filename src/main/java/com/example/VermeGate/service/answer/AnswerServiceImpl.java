package com.example.VermeGate.service.answer;

import com.example.VermeGate.dto.AnswerDto;
import com.example.VermeGate.entities.Answers;
import com.example.VermeGate.entities.Questions;
import com.example.VermeGate.entities.User;
import com.example.VermeGate.repository.AnswerRepository;
import com.example.VermeGate.repository.QuestionRepository;
import com.example.VermeGate.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AnswerServiceImpl implements  AnswerService{

    private final UserRepository userRepository;

    private QuestionRepository questionRepository;

    private AnswerRepository answerRepository;

    public AnswerServiceImpl(UserRepository userRepository, QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }


    @Override
    public AnswerDto postAnswer(AnswerDto answerDto) {
        Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
        Optional<Questions> optionalQuestion = questionRepository.findById(answerDto.getQuestionId());
        if (optionalUser.isPresent() && optionalQuestion.isPresent()) {
            Answers answer = new Answers();
            answer.setBody(answerDto.getBody());
            answer.setCreatedDate(new Date());
            answer.setUser(optionalUser.get());
            answer.setQuestion(optionalQuestion.get());

            Answers createdAnswers = answerRepository.save(answer);
            AnswerDto createdAnswersDto = new AnswerDto();
            createdAnswersDto.setId(createdAnswers.getId());
            createdAnswersDto.setBody(createdAnswers.getBody());
            return createdAnswersDto;

        }
        return null;
    }
}
