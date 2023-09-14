package com.example.VermeGate.repository;

import com.example.VermeGate.entities.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Questions, Long> {
    Optional<Questions> findByTitle(String questionTitle);
}
