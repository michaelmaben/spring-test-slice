package com.jeevanmaben.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class StudentCacheTest {
    @Autowired
    private StudentService studentService;

    @MockBean
    private StudentRepository studentRepository;
    @Test
    public void testGetStudentUsingCache(){
        //given
        Long id = 123l;
        given(studentRepository.findById(id)).willReturn(Optional.of(new Student(id, "Jeevan")));
        //when
        studentService.getStudentById(id);
        studentService.getStudentById(id);
        studentService.getStudentById(id);
        studentService.getStudentById(id);
        //then
        then(studentRepository).should(times(1)).findById(id);
    }
}
