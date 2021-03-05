package com.jeevanmaben.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;

import static org.assertj.core.api.BDDAssertions.then;


@DataJpaTest
public class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void testGetStudentByName(){
        //given
        testEntityManager.persistFlushFind(new Student(null, "Jeevan"));
        //when
        Student student = studentRepository.getStudentByName("Jeevan");
        //then
        then(student.getId()).isNotNull();
        then(student.getName()).isEqualTo("Jeevan");
    }

    @Test
    void testGetAverageGrade(){
        //given
        Student stud_1 = Student.builder().name("stud-1").active(true).grade(90).build();
        Student stud_2 = Student.builder().name("stud-2").active(true).grade(78).build();
        Student stud_3 = Student.builder().name("stud-3").active(false).grade(50).build();
        Arrays.asList(stud_1, stud_2, stud_3).forEach(testEntityManager::persistFlushFind);

        //when
        Double averageGrade =  studentRepository.getStudentAverageForActiveStudents();

        //then
        then(averageGrade).isEqualTo(84.0);
    }
}
