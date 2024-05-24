package com.org.LearningNavigator.controllers;

import com.org.LearningNavigator.dto.ExamDto;
import com.org.LearningNavigator.entities.Subject;
import com.org.LearningNavigator.services.ExamService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ExamController.class)
public class ExamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExamService examService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetExams() throws Exception {
        List<ExamDto> examList = new ArrayList<>();
        examList.add(new ExamDto(1L, new Subject(), new ArrayList<>()));

        Mockito.when(examService.getExams()).thenReturn(examList);

        mockMvc.perform(get("/exams"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(examList.size()));
    }

    @Test
    public void testGetExamById() throws Exception {
        ExamDto exam = new ExamDto(1L, new Subject(), new ArrayList<>());

        Mockito.when(examService.getExamById(anyLong())).thenReturn(exam);

        mockMvc.perform(get("/exams/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.examId").value(1L));
    }

    @Test
    public void testCreateExam() throws Exception {
        ExamDto exam = new ExamDto(1L, new Subject(1L, "Physics", new ArrayList<>(), new ArrayList<>()), new ArrayList<>());

        Mockito.when(examService.createExam(any(ExamDto.class))).thenReturn(exam);

        mockMvc.perform(post("/exams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(exam)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.examId").value(1L));
    }

    @Test
    public void testUpdateExam() throws Exception {
        ExamDto exam = new ExamDto(1L, new Subject(2L, "Chemistry", new ArrayList<>(), new ArrayList<>()), new ArrayList<>());

        Mockito.when(examService.updateExam(anyLong(), any(ExamDto.class))).thenReturn(exam);

        mockMvc.perform(put("/exams/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(exam)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.examId").value(1L));
    }

    @Test
    public void testDeleteExamById() throws Exception {
        mockMvc.perform(delete("/exams/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string("Exam with examId 1 deleted successfully...!!!"));
    }

    @Test
    public void testRegisterStudentToExam() throws Exception {
        mockMvc.perform(put("/exams/{id}/register/{studentId}", 1L, 1L))
                .andExpect(status().isOk())
                .andExpect(content().string("Student with studentId 1 registered successfully for the exam with examId 1"));
    }
}
