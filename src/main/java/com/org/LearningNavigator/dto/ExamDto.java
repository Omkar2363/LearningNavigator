package com.org.LearningNavigator.dto;

import com.org.LearningNavigator.entities.Student;
import com.org.LearningNavigator.entities.Subject;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamDto {

    private Long examId;

    private Subject examsubject;

    private List<Student> students;
}
