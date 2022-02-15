package com.example.examserver.interfaces.services;

import com.example.examserver.entities.exam.Quiz;

import java.util.List;

public interface QuizServiceInterface {
    public Quiz addQuiz(Quiz quiz);
    public Quiz getQuizById(Long id);
    public List<Quiz> getQuizzes();
    public Quiz updateQuiz(Quiz quiz);
    public void deleteQuiz(Long id);
}
