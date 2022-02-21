package com.example.examserver.services;

import com.example.examserver.entities.exam.Quiz;
import com.example.examserver.interfaces.services.QuizServiceInterface;
import com.example.examserver.repos.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService implements QuizServiceInterface {


    @Autowired
    QuizRepository quizRepository;
    @Override
    public Quiz addQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public Quiz getQuizById(Long id) {
        return quizRepository.findById(id).get();
    }

    @Override
    public List<Quiz> getQuizzes() {
        return quizRepository.findAll();
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) {
        Quiz quiz1=quizRepository.findById(quiz.getId()).get();
        return quiz1!=null?quizRepository.save(quiz):null;
    }

    @Override
    public void deleteQuiz(Long id) {
        Quiz quiz=getQuizById(id);
        quizRepository.delete(quiz);
    }
}
