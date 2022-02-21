package com.example.examserver.services;

import com.example.examserver.entities.exam.Questions;
import com.example.examserver.interfaces.services.QuestionServiceInterface;
import com.example.examserver.repos.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService implements QuestionServiceInterface {

    @Autowired
    QuestionRepository questionRepository;
    @Override
    public Questions addQuestion(Questions question) {
        return questionRepository.save(question);
    }

    @Override
    public Questions getQuestionById(Long id) {
        return questionRepository.findById(id).get();
    }

    @Override
    public List<Questions> getQuestions() {
        return questionRepository.findAll();
    }



    @Override
    public Questions updateQuestion(Questions question) {
        Questions questions=questionRepository.findById(question.getId()).get();
        return questions!=null?questionRepository.save(questions):null;
    }

    @Override
    public void deleteQuestion(Long id) {
        Questions questions=getQuestionById(id);
        questionRepository.delete(questions);
    }
}
