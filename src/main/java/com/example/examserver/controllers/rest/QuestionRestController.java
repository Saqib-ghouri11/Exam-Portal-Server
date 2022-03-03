package com.example.examserver.controllers.rest;

import com.example.examserver.entities.exam.Questions;
import com.example.examserver.entities.exam.Quiz;
import com.example.examserver.services.QuestionService;
import com.example.examserver.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/question")
public class QuestionRestController {

    @Autowired
    QuestionService questionService;

    @Autowired
    QuizService quizService;

    @PostMapping("/post")
    public Questions postQuestion(@RequestBody Questions questions){
        return questionService.addQuestion(questions);
    }

    @GetMapping("/{id}")
    public Questions getQuestionById(@PathVariable("id") Long id){
        return questionService.getQuestionById(id);
    }

    @GetMapping("/")
    public List<Questions> getQuestions(){
        return questionService.getQuestions();
    }

    //this is for user
    @GetMapping("/quiz/{id}")
    public List<Questions> getQuestionsOfQuizById(@PathVariable("id") Long id){
        Quiz quiz = quizService.getQuizById(id);
       Set<Questions> questions=quiz.getSetOfQuestions();
        List<Questions> quizQuestions=new ArrayList<>();
        int maxLength=quiz.getNumberOfQuestions();
        int count=0;
        for(Questions ques:questions){
                if(count<maxLength) {
                    quizQuestions.add(ques);
                }
                count++;

        }
        return quizQuestions;
    }
    
    //this is for admin
    @GetMapping("/quiz/admin/{id}")
    public List<Questions> getAllQuestionsOfQuizById(@PathVariable("id") Long id){
        Quiz quiz = quizService.getQuizById(id);
       Set<Questions> questions=quiz.getSetOfQuestions();
        List<Questions> quizQuestions=questions.stream().collect(Collectors.toList());;
       
        return quizQuestions;
    }

    @PutMapping("/put")
    public Questions updateQuestion(@RequestBody Questions questions){
        Questions questions1=questionService.getQuestionById(questions.getId());
        return questions1!=null?questionService.updateQuestion(questions):null;
    }

    @SuppressWarnings("unchecked")
	@DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteQuestionById(@PathVariable("id") Long id){
        Map map=new HashMap<>();
        try {
            questionService.deleteQuestion(id);
            map.put("message","deleted");
        }catch (Exception e){
            e.printStackTrace();
            map.put("message","failed : "+e.getMessage());
            return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(map);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ MethodArgumentNotValidException.class,Exception.class})
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,Exception e) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        Arrays.stream(e.getStackTrace()).forEach(error->{
            errors.put("error",e.getMessage());
        });

        return errors;
    }
}
