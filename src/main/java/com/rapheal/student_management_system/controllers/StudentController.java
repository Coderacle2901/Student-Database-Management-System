package com.rapheal.student_management_system.controllers;

import com.rapheal.student_management_system.DTOs.StudentDTO;
import com.rapheal.student_management_system.entities.Student;
import com.rapheal.student_management_system.services.StudentService;
import com.rapheal.student_management_system.services.StudentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
@Autowired
    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    //Rest api for adding students to the database
    @PostMapping
    public ResponseEntity<?> createStudent( @Valid @RequestBody StudentDTO studentDto){

        Student student = new Student();
        student.setAge(studentDto.getAge());
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setEmail(studentDto.getEmail());


            Student savedStudent = studentService.addStudent(student);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);

    }

    //Rest api for getting a list of all students from the database
    @GetMapping("/list")
    public ResponseEntity<List<Student>> getStudents(){

    List<Student> list = studentService.getALLStudents();

    return ResponseEntity.ok(list);
    }


    //Rest api for finding a student by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id){

    Optional<Student> student = studentService.getStudentById(id);

    if(student.isPresent()){

        return ResponseEntity.ok(student.get());

    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Couldn't find student with said id" + id);
    }


    // Rest api for partially updating student data
@PatchMapping("/{id}")
    public ResponseEntity<?> updateStudentInfo(@PathVariable Long id, @RequestBody Map<String,Object> updateData){

      Student student = studentService.updateStudentPartially(id,updateData);

      return ResponseEntity.ok(student);

    }


    // Rest api for updating an existing student's info
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudentInfo(@PathVariable Long id, @RequestBody Student student){

    Optional<Student> optionalStudent = studentService.getStudentById(id);

    if(optionalStudent.isEmpty()){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("Message","Student with  id:" + id + " not found"));
    }


    Student matchingStudent = optionalStudent.get();

    matchingStudent.setEmail(student.getEmail());

    matchingStudent.setAge(student.getAge());

    matchingStudent.setLastName(student.getLastName());

    matchingStudent.setFirstName(student.getFirstName());

    Student updatedStudent = studentService.addStudent(matchingStudent);

    return ResponseEntity.ok(updatedStudent);
    }


    // Rest api for deleting student data
    @DeleteMapping("/{Id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long Id){
        studentService.deleteStudentById(Id);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

  }

    }

