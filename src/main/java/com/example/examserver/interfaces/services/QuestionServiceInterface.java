package com.example.examserver.interfaces.services;

import com.example.examserver.entities.exam.Questions;

import java.util.List;

public interface QuestionServiceInterface {
    public Questions addQuestion(Questions question);
    public Questions getQuestionById(Long id);
    public List<Questions> getQuestions();
    public Questions updateQuestion(Questions question);
    public void deleteQuestion(Long id);
}
