package com.jeevanmaben.test;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class StudentServiceTest {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentService studentService;

    @Test
    public void testStudentService() throws Exception{

        //given
        Student student = studentRepository.save(Student.builder().name("Jeevan").active(true).grade(100).build());
        //when
        Student retrievedStudent = studentService.getStudentById(student.getId());
        //then
        then(retrievedStudent.getId()).isNotNull();
        then(retrievedStudent.getName()).isEqualTo("Jeevan");
        then(retrievedStudent.getGrade()).isEqualTo(100);
    }

    @Test
    public void testGetStudentNotPresent(){
        //given a non-existing student
        Long id = 1234l;
        //when
        Throwable throwable = catchThrowable( () -> studentService.getStudentById(id));
        //then
        BDDAssertions.then(throwable).isInstanceOf(RuntimeException.class);
    }

}
