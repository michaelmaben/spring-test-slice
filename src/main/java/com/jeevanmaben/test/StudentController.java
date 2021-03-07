package com.jeevanmaben.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable Long id) throws Exception{
        return studentService.getStudentById(id);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void handleMissingStudent(StudentNotFoundException e){

    }
}
