package com.jeevanmaben.test;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Cacheable("students")
    public Student getStudentById(Long id) throws StudentNotFoundException{
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException());
    }
}
