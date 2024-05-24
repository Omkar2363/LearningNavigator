package com.org.LearningNavigator.services;

import com.org.LearningNavigator.dto.StudentDto;
import com.org.LearningNavigator.entities.Student;
import com.org.LearningNavigator.exceptions.ResourceNotFoundException;
import com.org.LearningNavigator.repositories.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<StudentDto> getStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentDtos = students.stream()
                                                .map(student -> modelMapper.map(student, StudentDto.class))
                                                .collect(Collectors.toList());
        return studentDtos;
    }

    @Override
    public StudentDto getStudentById(Long registrationId) {
        Student student = studentRepository.findById(registrationId)
                                            .orElseThrow(()-> new ResourceNotFoundException("Student with registrationId "+ registrationId + " not found...."));
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public StudentDto registerStudent(StudentDto studentDto) {
        Student student = modelMapper.map(studentDto, Student.class);
        Student savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, StudentDto.class);
    }

    @Override
    public StudentDto updateStudent(Long registrationId, StudentDto studentDto) {
        Student student = studentRepository.findById(registrationId)
                .orElseThrow(()-> new ResourceNotFoundException("Student with registrationId "+ registrationId + " not found...."));

        student.setName(studentDto.getName());

        Student savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, StudentDto.class);
    }

    @Override
    public void deleteStudentById(Long registrationId) {
        Student student = studentRepository.findById(registrationId)
                .orElseThrow(()-> new ResourceNotFoundException("Student with registrationId "+ registrationId + " not found...."));

        studentRepository.delete(student);
    }
}
