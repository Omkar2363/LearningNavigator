package com.org.LearningNavigator.services;

import com.org.LearningNavigator.dto.ExamDto;
import com.org.LearningNavigator.entities.Exam;
import com.org.LearningNavigator.entities.Student;
import com.org.LearningNavigator.entities.Subject;
import com.org.LearningNavigator.exceptions.ResourceNotFoundException;
import com.org.LearningNavigator.repositories.ExamRepository;
import com.org.LearningNavigator.repositories.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ExamServiceImplTest {

    @InjectMocks
    private ExamServiceImpl examService;

    @Mock
    private ExamRepository examRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private ModelMapper modelMapper;

    private Exam exam;
    private ExamDto examDto;
    private Student student;
    private Subject subject;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        exam = new Exam();
        exam.setExamId(1L);
        exam.setExamsubject(new Subject(1L, "Physics", new ArrayList<Student>(), new ArrayList<Exam>()));

        examDto = new ExamDto();
        examDto.setExamId(1L);

        student = new Student();
        student.setRegistrationId(1L);


        subject = new Subject();
        subject.setSubjectId(1L);
        subject.setStudents(new ArrayList<>());
    }


    @Test
    public void testGetExams() {
        List<Exam> exams = List.of(exam);
        List<ExamDto> examDtos = List.of(examDto);

        when(examRepository.findAll()).thenReturn(exams);
        when(modelMapper.map(any(Exam.class), eq(ExamDto.class))).thenReturn(examDto);

        List<ExamDto> result = examService.getExams();

        assertEquals(examDtos, result);
        verify(examRepository, times(1)).findAll();
    }

    @Test
    public void testGetExamById() {
        when(examRepository.findById(1L)).thenReturn(Optional.of(exam));
        when(modelMapper.map(any(Exam.class), eq(ExamDto.class))).thenReturn(examDto);

        ExamDto result = examService.getExamById(1L);

        assertEquals(examDto, result);
        verify(examRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetExamById_NotFound() {
        when(examRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> examService.getExamById(1L));
        verify(examRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateExam() {
        when(modelMapper.map(any(ExamDto.class), eq(Exam.class))).thenReturn(exam);
        when(examRepository.save(any(Exam.class))).thenReturn(exam);
        when(modelMapper.map(any(Exam.class), eq(ExamDto.class))).thenReturn(examDto);

        ExamDto result = examService.createExam(examDto);

        assertEquals(examDto, result);
        verify(examRepository, times(1)).save(exam);
    }


    @Test
    public void testUpdateExam_NotFound() {
        when(examRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> examService.updateExam(1L, examDto));
        verify(examRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteExamById() {
        when(examRepository.findById(1L)).thenReturn(Optional.of(exam));

        examService.deleteExamById(1L);

        verify(examRepository, times(1)).findById(1L);
        verify(examRepository, times(1)).delete(exam);
    }

    @Test
    public void testDeleteExamById_NotFound() {
        when(examRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> examService.deleteExamById(1L));
        verify(examRepository, times(1)).findById(1L);
    }


    @Test
    public void testRegisterStudentToExam_ExamNotFound() {
        when(examRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> examService.registerStudentToExam(1L, 1L));
        verify(examRepository, times(1)).findById(1L);
    }

    @Test
    public void testRegisterStudentToExam_StudentNotFound() {
        when(examRepository.findById(1L)).thenReturn(Optional.of(exam));
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> examService.registerStudentToExam(1L, 1L));
        verify(examRepository, times(1)).findById(1L);
        verify(studentRepository, times(1)).findById(1L);
    }

    @Test
    public void testRegisterStudentToExam_NotEnrolledInSubject() {
        when(examRepository.findById(1L)).thenReturn(Optional.of(exam));
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        assertThrows(ResourceNotFoundException.class, () -> examService.registerStudentToExam(1L, 1L));
        verify(examRepository, times(1)).findById(1L);
        verify(studentRepository, times(1)).findById(1L);
    }
}
