package com.example.examserver.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.examserver.entities.exam.Category;
import com.example.examserver.entities.exam.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz,Long> {
    Quiz findByTitle(String title);
    List<Quiz> findAllByActive(boolean active);
    List<Quiz> findAllByCategoryAndActive(Category category,boolean active);
}
