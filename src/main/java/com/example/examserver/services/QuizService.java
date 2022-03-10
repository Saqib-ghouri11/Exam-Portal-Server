package com.example.examserver.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.examserver.entities.exam.Category;
import com.example.examserver.entities.exam.Quiz;
import com.example.examserver.interfaces.services.QuizServiceInterface;
import com.example.examserver.repos.QuizRepository;

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

	@Override
	public List<Quiz> getQuizByActive(boolean active) {
		// TODO Auto-generated method stub
		return quizRepository.findAllByActive(active);
	}

	@Override
	public List<Quiz> getQuizByCategoryAndActive(Category category,boolean active) {
		// TODO Auto-generated method stub
		return quizRepository.findAllByCategoryAndActive(category,active);
	}
}
