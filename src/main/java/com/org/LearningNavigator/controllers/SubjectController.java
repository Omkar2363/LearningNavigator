package com.org.LearningNavigator.controllers;

import com.org.LearningNavigator.dto.SubjectDto;
import com.org.LearningNavigator.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {


    @Autowired
    private SubjectService subjectService;


    @GetMapping
    public ResponseEntity<List<SubjectDto>> getSubjects(){
        return ResponseEntity.ok().body(subjectService.getSubjects());
    }


    @GetMapping("/{id}")
    public ResponseEntity<SubjectDto> getSubjectById(@PathVariable Long id){
        return ResponseEntity.ok().body(subjectService.getSubjectById(id));
    }

    @PostMapping
    public ResponseEntity<SubjectDto> createSubject(@RequestBody SubjectDto subjectDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(subjectService.createSubject(subjectDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubjectDto> updateSubject(@PathVariable Long id, @RequestBody SubjectDto subjectDto){
        return ResponseEntity.ok().body(subjectService.updateSubject(id, subjectDto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable Long id) {
        subjectService.deleteSubjectById((id));

        return ResponseEntity.ok().body("Subject with subjectId " + id + " deleted successfully...!!!");
    }


    @PutMapping("/{id}"+ "/enrollStudent" + "/{studentId}")
    public ResponseEntity<SubjectDto> enrollStudentForSubject(@PathVariable Long id, @PathVariable Long studentId){
        return ResponseEntity.ok().body(subjectService.enrollStudent(id, studentId));
    }


}
