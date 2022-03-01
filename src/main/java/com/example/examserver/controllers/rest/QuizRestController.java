package com.example.examserver.controllers.rest;

import com.example.examserver.entities.exam.Quiz;
import com.example.examserver.services.QuizService;
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
@RequestMapping("/quiz")
@CrossOrigin
public class QuizRestController {

    @Autowired
    QuizService quizService;

    @GetMapping("/")
    public List<Quiz> getQuizzes(){
        return quizService.getQuizzes();
    }

    @GetMapping("/{id}")
    public Quiz getQUizById(@PathVariable("id") Long id){
        return quizService.getQuizById(id);
    }

    @PostMapping("/post")
    public Quiz postQuiz(@RequestBody Quiz quiz){
        return quizService.addQuiz(quiz);
    }

    @PutMapping("/put")
    public ResponseEntity<?> updateQuiz(@RequestBody Quiz quiz){
       
        return ResponseEntity.ok(quizService.updateQuiz(quiz)!=null?"{\"message\":\"updated\"}":"{\"message\":\"Quiz not found\"}");
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteQuizById(@PathVariable("id") Long id){
        Map map=new HashMap<>();
        try {
            quizService.deleteQuiz(id);
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
