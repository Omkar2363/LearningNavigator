package com.org.LearningNavigator.controllers;

import com.org.LearningNavigator.dto.StudentDto;
import com.org.LearningNavigator.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController{

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDto>> getStudents(){
        return ResponseEntity.ok().body(studentService.getStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id){
        return ResponseEntity.ok().body(studentService.getStudentById(id));
    }

    @PostMapping
    public ResponseEntity<StudentDto> registerStudent(@RequestBody StudentDto studentDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.registerStudent(studentDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id, @RequestBody StudentDto studentDto){
        return ResponseEntity.ok().body(studentService.updateStudent(id, studentDto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable Long id) {
        studentService.deleteStudentById((id));

        return ResponseEntity.ok().body("Student with registrationId " + id + " deleted successfully...!!!");
    }


}