package com.org.LearningNavigator.dto;

import com.org.LearningNavigator.entities.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDto {

    private Long subjectId;

    private String name;

    private List<Student> students;
}
