package com.jeevanmaben.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class StudentServiceTest {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentService studentService;

    @Test
    public void testStudentService(){

        //given
        Student student = studentRepository.save(Student.builder().name("Jeevan").active(true).grade(100).build());
        //when
        Student retrievedStudent = studentService.getStudentById(student.getId());
        //then
        then(retrievedStudent.getId()).isNotNull();
        then(retrievedStudent.getName()).isEqualTo("Jeevan");
        then(retrievedStudent.getGrade()).isEqualTo(100);
    }
}
