package com.example.examserver.repos;

import com.example.examserver.entities.exam.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Questions,Long> {
    Questions findByContent(String content);
}
