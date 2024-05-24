package com.org.LearningNavigator.dto;

import com.org.LearningNavigator.entities.Exam;
import com.org.LearningNavigator.entities.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    private Long registrationId;
    private String name;
    private List<Subject> subjects;
    private List<Exam> exams;

}
