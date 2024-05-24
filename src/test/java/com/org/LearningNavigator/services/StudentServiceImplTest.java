package com.org.LearningNavigator.services;

import com.org.LearningNavigator.dto.StudentDto;
import com.org.LearningNavigator.entities.Student;
import com.org.LearningNavigator.exceptions.ResourceNotFoundException;
import com.org.LearningNavigator.repositories.StudentRepository;
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
public class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private StudentServiceImpl studentService;

    private Student student;
    private StudentDto studentDto;

    @BeforeEach
    void setUp() {
        student = new Student();
        student.setRegistrationId(1L);
        student.setName("John Doe");

        studentDto = new StudentDto();
        studentDto.setRegistrationId(1L);
        studentDto.setName("John Doe");
    }

    @Test
    void testGetStudents() {
        List<Student> students = Arrays.asList(student);
        when(studentRepository.findAll()).thenReturn(students);
        when(modelMapper.map(any(Student.class), eq(StudentDto.class))).thenReturn(studentDto);

        List<StudentDto> result = studentService.getStudents();

        assertEquals(1, result.size());
        verify(studentRepository, times(1)).findAll();
        verify(modelMapper, times(1)).map(any(Student.class), eq(StudentDto.class));
    }

    @Test
    void testGetStudentById() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(modelMapper.map(any(Student.class), eq(StudentDto.class))).thenReturn(studentDto);

        StudentDto result = studentService.getStudentById(1L);

        assertEquals(studentDto, result);
        verify(studentRepository, times(1)).findById(1L);
        verify(modelMapper, times(1)).map(any(Student.class), eq(StudentDto.class));
    }

    @Test
    void testGetStudentByIdNotFound() {
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> studentService.getStudentById(1L));
        verify(studentRepository, times(1)).findById(1L);
        verify(modelMapper, never()).map(any(Student.class), eq(StudentDto.class));
    }

    // Add tests for registerStudent, updateStudent, and deleteStudentById methods
}