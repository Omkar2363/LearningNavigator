package com.org.LearningNavigator.services;

import com.org.LearningNavigator.dto.ExamDto;
import com.org.LearningNavigator.entities.Exam;
import com.org.LearningNavigator.entities.Student;
import com.org.LearningNavigator.entities.Subject;
import com.org.LearningNavigator.exceptions.ResourceNotFoundException;
import com.org.LearningNavigator.repositories.ExamRepository;
import com.org.LearningNavigator.repositories.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamServiceImpl implements ExamService{

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<ExamDto> getExams() {
        List<Exam> exams = examRepository.findAll();
        List<ExamDto> examDtos = exams.stream()
                                        .map(exam -> modelMapper.map(exam, ExamDto.class))
                                        .collect(Collectors.toList());
        return examDtos;
    }

    @Override
    public ExamDto getExamById(Long examId) {
        Exam exam = examRepository.findById(examId)
                                    .orElseThrow(() -> new ResourceNotFoundException("Exam with examId "+ examId +" not found...!!!"));
        return modelMapper.map(exam, ExamDto.class);
    }

    @Override
    public ExamDto createExam(ExamDto examDto) {
        Exam exam = modelMapper.map(examDto, Exam.class);
        Exam savedExam = examRepository.save(exam);
        return modelMapper.map(savedExam, ExamDto.class);
    }

    @Override
    public ExamDto updateExam(Long examId, ExamDto examDto) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new ResourceNotFoundException("Exam with examId "+ examId +" not found...!!!"));

        exam.setExamsubject(examDto.getExamsubject());
        Exam updatedExam = examRepository.save(exam);

        return modelMapper.map(updatedExam, ExamDto.class);
    }

    @Override
    public void deleteExamById(Long examId) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new ResourceNotFoundException("Exam with examId "+ examId +" not found...!!!"));

        examRepository.delete(exam);
    }

    @Override
    public void registerStudentToExam(Long examId, Long studentId) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new ResourceNotFoundException("Exam with examId "+ examId +" not found...!!!"));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Exam with examId "+ examId +" not found...!!!"));

        //Get the subject of the exam :
        Subject subject = exam.getExamsubject();

        if(!subject.getStudents().contains(student)) {
            throw new ResourceNotFoundException("First enroll in the subject to give this exam...!!!");
        }

        if (!exam.getStudents().contains(student)) {
            exam.getStudents().add(student);
            student.getExams().add(exam);
            examRepository.save(exam);
        }
    }
}
