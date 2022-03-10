package com.example.examserver.interfaces.services;

import java.util.List;

import com.example.examserver.entities.exam.Category;
import com.example.examserver.entities.exam.Quiz;

public interface QuizServiceInterface {
    public Quiz addQuiz(Quiz quiz);
    public Quiz getQuizById(Long id);
    public List<Quiz> getQuizzes();
    public Quiz updateQuiz(Quiz quiz);
    public void deleteQuiz(Long id);
    public List<Quiz> getQuizByActive(boolean active);
    public List<Quiz> getQuizByCategoryAndActive(Category category,boolean active);
}
