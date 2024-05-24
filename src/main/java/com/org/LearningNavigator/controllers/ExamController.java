package com.org.LearningNavigator.controllers;

import com.org.LearningNavigator.dto.ExamDto;
import com.org.LearningNavigator.services.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exams")
public class ExamController {

    @Autowired
    public ExamService examService;

    @GetMapping
    public ResponseEntity<List<ExamDto>> getExams() {
        return ResponseEntity.ok().body(examService.getExams());
    }


    @GetMapping("/{id}")
    public ResponseEntity<ExamDto> getExamById(@PathVariable Long id) {
        return ResponseEntity.ok().body(examService.getExamById(id));
    }


    @PostMapping
    public ResponseEntity<ExamDto> createExam(@RequestBody ExamDto examDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(examService.createExam(examDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamDto> updateExam(@PathVariable Long id, @RequestBody ExamDto examDto) {
        return ResponseEntity.ok().body(examService.updateExam(id, examDto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExamById(@PathVariable Long id) {
        examService.deleteExamById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Exam with examId " + id + " deleted successfully...!!!");
    }


    @PutMapping("/{id}" + "/register/{studentId}")
    public ResponseEntity<String> registerStudentToExam(@PathVariable Long id, @PathVariable Long studentId) {

        examService.registerStudentToExam(id, studentId);

        return ResponseEntity.ok().body("Student with studentId "+ studentId +" registered successfully for the exam with examId "+ id);

    }

}
