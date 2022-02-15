package com.example.examserver.repos;

import com.example.examserver.entities.exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz,Long> {
    Quiz findByTitle(String title);
}
