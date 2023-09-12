package com.example.VermeGate.entities;

import com.example.VermeGate.dto.QuestionDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


import java.util.Date;

@Entity
@Data
@Table(name = "questions")
public class Questions {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String title;

        @Lob
        @Column(name = "body", length = 512)
        private String body;
        private Date createdDate;
        private String tags;

        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "user_id", nullable = false)
        @OnDelete(action = OnDeleteAction.CASCADE)
        @JsonIgnore
        private User user;


        public QuestionDto getQuestionDto() {
                QuestionDto questionDto = new QuestionDto();
                questionDto.setId(id);
                questionDto.setTitle(title);
                questionDto.setBody(body);
                questionDto.setTags(tags);
                questionDto.setCreatedDate(createdDate);
                questionDto.setUserId(user.getId());
                questionDto.setUsername(user.getName());
                return questionDto;
        }
}
