package com.jeevanmaben.test;

import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Long>{
    Student getStudentByName(String name);

    @Query("Select avg(grade) from Student where active = true")
    Double getStudentAverageForActiveStudents();
}
