package com.example.university_backend.repository;

import com.example.university_backend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT s FROM Student s where s.department = :course")
    List<Student> findByCourse(@Param("course") String course);
}
