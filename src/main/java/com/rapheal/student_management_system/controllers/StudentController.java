package com.rapheal.student_management_system.controllers;

import com.rapheal.student_management_system.Entities.Student;
import com.rapheal.student_management_system.services.StudentService;
import com.rapheal.student_management_system.services.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
@Autowired
    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    //REst api for adding students to the database
    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody Student student){
        try{
            Student savedStudent = studentService.addStudent(student);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
        } catch (DataIntegrityViolationException e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid student details" + e.getMessage());
        } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed to save students" + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Student>> getStudents(){
    List<Student> list = studentService.getALLStudents();
    return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id){
    Optional<Student> student = studentService.getStudentById(id);
    if(student.isPresent()){
        return ResponseEntity.ok(student.get());
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Couldn't find student with said id" + id);
    }

@PatchMapping("/{id}")
    public ResponseEntity<?> updateStudentInfo(@PathVariable Long id, @RequestBody Map<String,Object> updateData){
  try{
      Student student = studentService.updateStudentPartially(id,updateData);
      return ResponseEntity.ok(student);
  }catch(NoSuchElementException e){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No matching student found" + e.getMessage());
  }catch(Exception e){
      return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
  }

    }

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

    @DeleteMapping("/{Id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long Id){
  try{
      studentService.deleteStudentById(Id);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  } catch (EmptyResultDataAccessException e) {
     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message",e.getMessage()));
  }catch (Exception e){
      return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "An unexpected error occurred " + e.getMessage()));
  }
    }

}
