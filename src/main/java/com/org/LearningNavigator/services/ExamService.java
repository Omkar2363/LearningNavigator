package com.org.LearningNavigator.services;

import com.org.LearningNavigator.dto.ExamDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExamService {

    List<ExamDto> getExams();
    ExamDto getExamById(Long examId);
    ExamDto createExam(ExamDto examDto);
    ExamDto updateExam(Long examId, ExamDto examDto);
    void deleteExamById(Long examId);

    void registerStudentToExam(Long examId, Long studentId);
}