package com.rapheal.student_management_system.services;

import com.rapheal.student_management_system.entities.Student;
import com.rapheal.student_management_system.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

//Constructor dependency injections
    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public Student addStudent(Student student) {
    return studentRepository.save(student);
    }

    @Override
    public List<Student> getALLStudents() {
       return studentRepository.findAll();
    }

    @Override
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public void deleteStudentById(Long id) {
        if(!studentRepository.existsById(id)){
            throw new EmptyResultDataAccessException("Student not found", 1);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public Student updateStudentPartially(Long id, Map<String, Object> updateData) {
       Optional<Student> matchingStudent = studentRepository.findById(id);
       Student student = matchingStudent.orElseThrow(() -> new NoSuchElementException("No matching student object found"));

        for (Map.Entry<String, Object> entry : updateData.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            switch (key) {
                case "firstName" -> student.setFirstName((String) value);
                case "lastName" -> student.setLastName((String) value);
                case "age" -> student.setAge(Integer.parseInt(value.toString()));
                case "email" -> student.setEmail((String) value);
            }
        }
        return studentRepository.save(student);
    }


}
