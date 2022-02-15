package com.example.examserver.controllers.rest;

import com.example.examserver.entities.exam.Questions;
import com.example.examserver.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/question")
public class QuestionRestController {

    @Autowired
    QuestionService questionService;

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

    @PutMapping("/put")
    public Questions updateQuestion(@RequestBody Questions questions){
        Questions questions1=questionService.getQuestionById(questions.getId());
        return questions1!=null?questionService.updateQuestion(questions):null;
    }

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
