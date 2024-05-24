package com.org.LearningNavigator.services;

import com.org.LearningNavigator.dto.SubjectDto;
import com.org.LearningNavigator.entities.Student;
import com.org.LearningNavigator.entities.Subject;
import com.org.LearningNavigator.exceptions.ResourceNotFoundException;
import com.org.LearningNavigator.repositories.StudentRepository;
import com.org.LearningNavigator.repositories.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectServiceIml implements SubjectService{

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<SubjectDto> getSubjects() {
        List<Subject> subjects = subjectRepository.findAll();

        List<SubjectDto> subjectDtos = subjects.stream()
                                                .map(subject -> modelMapper.map(subject, SubjectDto.class))
                                                .collect(Collectors.toList());
        return subjectDtos;
    }

    @Override
    public SubjectDto getSubjectById(Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId)
                                            .orElseThrow(()-> new ResourceNotFoundException("Subject with subjectId "+ subjectId +" not found...!!!"));
        return modelMapper.map(subject, SubjectDto.class);
    }

    @Override
    public SubjectDto createSubject(SubjectDto subjectDto) {
        Subject subject = modelMapper.map(subjectDto, Subject.class);
        Subject savedSubject = subjectRepository.save(subject);
        return modelMapper.map(savedSubject, SubjectDto.class);
    }

    @Override
    public SubjectDto updateSubject(Long subjectId, SubjectDto subjectDto) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(()-> new ResourceNotFoundException("Subject with subjectId "+ subjectId +" not found...!!!"));

        subject.setName(subjectDto.getName());
        Subject savedSubject = subjectRepository.save(subject);
        return modelMapper.map(savedSubject, SubjectDto.class);
    }

    @Override
    public void deleteSubjectById(Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(()-> new ResourceNotFoundException("Subject with subjectId "+ subjectId +" not found...!!!"));

        subjectRepository.delete(subject);

    }

    @Override
    public SubjectDto enrollStudent(Long subjectId, Long studentId) {
        System.out.println("\n\n\nInside service layer");
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(()-> new ResourceNotFoundException("Subject with subjectId "+ subjectId +" not found...!!!"));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(()-> new ResourceNotFoundException("Student with registrationId "+ studentId + " not found...."));


        // Adding the student to the subject's list of students
        subject.getStudents().add(student);

        // Adding the subject to the student's list of subjects (ensure bidirectional consistency)
        student.getSubjects().add(subject);

        // Save the updated subject (cascading will handle the student save)
        Subject updatedSubject = subjectRepository.save(subject);

        // Print the updated subject (for debugging purposes)
        System.out.println(updatedSubject);


        return modelMapper.map(updatedSubject, SubjectDto.class);
    }
}
