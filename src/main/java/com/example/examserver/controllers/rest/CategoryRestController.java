package com.example.examserver.controllers.rest;

import com.example.examserver.entities.exam.Category;
import com.example.examserver.services.CategoryService;
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
@RequestMapping("/category")
@CrossOrigin(origins = "*")
public class CategoryRestController {

    @Autowired
    CategoryService categoryService;
    @GetMapping("/")
    public List<Category> getCategories(){
        return categoryService.getCategories();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable("id") Long id){
        return categoryService.getCategoryById(id);
    }

    @PostMapping("/post")
    public Category saveCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }

    @PutMapping("/put")
    public Category updateCategory(@RequestBody Category category){
        return categoryService.updateCategory(category);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable("id") Long id){

        Map map=new HashMap();
        try {
            categoryService.deleteCategory(id);
            map.put("message","deleted");
        }catch (Exception e){
            e.printStackTrace();
            map.put("message","failed : "+e.getMessage());
            return new ResponseEntity(map,HttpStatus.BAD_REQUEST);
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
