package com.org.LearningNavigator.services;

import com.org.LearningNavigator.dto.SubjectDto;
import com.org.LearningNavigator.entities.Student;
import com.org.LearningNavigator.entities.Subject;
import com.org.LearningNavigator.exceptions.ResourceNotFoundException;
import com.org.LearningNavigator.repositories.StudentRepository;
import com.org.LearningNavigator.repositories.SubjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SubjectServiceImplTest {

    @Mock
    private SubjectRepository subjectRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private SubjectServiceIml subjectService;

    private Subject subject;
    private SubjectDto subjectDto;
    private Student student;

    @BeforeEach
    void setUp() {
        subject = new Subject();
        subject.setSubjectId(1L);
        subject.setName("Mathematics");

        subjectDto = new SubjectDto();
        subjectDto.setSubjectId(1L);
        subjectDto.setName("Mathematics");

        student = new Student();
        student.setRegistrationId(1L);
        student.setName("John Doe");
    }

    @Test
    void testGetSubjects() {
        List<Subject> subjects = Arrays.asList(subject);
        when(subjectRepository.findAll()).thenReturn(subjects);
        when(modelMapper.map(any(Subject.class), eq(SubjectDto.class))).thenReturn(subjectDto);

        List<SubjectDto> result = subjectService.getSubjects();

        assertEquals(1, result.size());
        verify(subjectRepository, times(1)).findAll();
        verify(modelMapper, times(1)).map(any(Subject.class), eq(SubjectDto.class));
    }

    @Test
    void testGetSubjectById() {
        when(subjectRepository.findById(1L)).thenReturn(Optional.of(subject));
        when(modelMapper.map(any(Subject.class), eq(SubjectDto.class))).thenReturn(subjectDto);

        SubjectDto result = subjectService.getSubjectById(1L);

        assertEquals(subjectDto, result);
        verify(subjectRepository, times(1)).findById(1L);
        verify(modelMapper, times(1)).map(any(Subject.class), eq(SubjectDto.class));
    }

    @Test
    void testGetSubjectByIdNotFound() {
        when(subjectRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> subjectService.getSubjectById(1L));
        verify(subjectRepository, times(1)).findById(1L);
        verify(modelMapper, never()).map(any(Subject.class), eq(SubjectDto.class));
    }

    @Test
    void testCreateSubject() {
        when(modelMapper.map(any(SubjectDto.class), eq(Subject.class))).thenReturn(subject);
        when(subjectRepository.save(any(Subject.class))).thenReturn(subject);
        when(modelMapper.map(any(Subject.class), eq(SubjectDto.class))).thenReturn(subjectDto);

        SubjectDto result = subjectService.createSubject(subjectDto);

        assertEquals(subjectDto, result);
        verify(modelMapper, times(1)).map(any(SubjectDto.class), eq(Subject.class));
        verify(subjectRepository, times(1)).save(any(Subject.class));
        verify(modelMapper, times(1)).map(any(Subject.class), eq(SubjectDto.class));
    }

    @Test
    void testUpdateSubject() {
        when(subjectRepository.findById(1L)).thenReturn(Optional.of(subject));
        when(subjectRepository.save(any(Subject.class))).thenReturn(subject);
        when(modelMapper.map(any(Subject.class), eq(SubjectDto.class))).thenReturn(subjectDto);

        SubjectDto result = subjectService.updateSubject(1L, subjectDto);

        assertEquals(subjectDto, result);
        verify(subjectRepository, times(1)).findById(1L);
        verify(subjectRepository, times(1)).save(any(Subject.class));
        verify(modelMapper, times(1)).map(any(Subject.class), eq(SubjectDto.class));
    }

    @Test
    void testDeleteSubjectById() {
        when(subjectRepository.findById(1L)).thenReturn(Optional.of(subject));

        subjectService.deleteSubjectById(1L);

        verify(subjectRepository, times(1)).findById(1L);
        verify(subjectRepository, times(1)).delete(subject);
    }

}