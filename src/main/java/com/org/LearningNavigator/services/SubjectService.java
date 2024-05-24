package com.org.LearningNavigator.services;


import com.org.LearningNavigator.dto.SubjectDto;

import java.util.List;

public interface SubjectService {

    List<SubjectDto> getSubjects();
    SubjectDto getSubjectById(Long subjectId);
    SubjectDto createSubject(SubjectDto subjectDto);
    SubjectDto updateSubject(Long subjectId, SubjectDto subjectDto);
    void deleteSubjectById(Long subjectId);

    SubjectDto enrollStudent(Long subjectId, Long studentId);

}

