package com.jeevanmaben.test;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }
}
