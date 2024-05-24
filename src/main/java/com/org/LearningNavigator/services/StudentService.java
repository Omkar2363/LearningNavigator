package com.org.LearningNavigator.services;


import com.org.LearningNavigator.dto.StudentDto;

import java.util.List;

public interface StudentService {
    List<StudentDto> getStudents();
    StudentDto getStudentById(Long registrationId);
    StudentDto registerStudent(StudentDto studentDto);
    StudentDto updateStudent(Long registrationId, StudentDto studentDto);
    void deleteStudentById(Long registrationId);

}
