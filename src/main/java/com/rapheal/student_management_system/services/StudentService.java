package com.rapheal.student_management_system.services;

import com.rapheal.student_management_system.entities.Student;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface StudentService {
    Student addStudent(Student student);
    List<Student> getALLStudents();
    Optional<Student> getStudentById(Long id);
    void deleteStudentById(Long id);
    Student updateStudentPartially(Long id, Map<String,Object> updateData);
}
