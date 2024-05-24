package com.org.LearningNavigator.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.LearningNavigator.dto.StudentDto;
import com.org.LearningNavigator.services.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetStudents() throws Exception {
        List<StudentDto> studentList = new ArrayList<>();
        studentList.add(new StudentDto(1L, "John Doe", new ArrayList<>(), new ArrayList<>()));

        Mockito.when(studentService.getStudents()).thenReturn(studentList);

        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(studentList.size()));
    }

    @Test
    public void testGetStudentById() throws Exception {
        StudentDto student = new StudentDto(1L, "John Doe", new ArrayList<>(), new ArrayList<>());

        Mockito.when(studentService.getStudentById(anyLong())).thenReturn(student);

        mockMvc.perform(get("/students/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.registrationId").value(1L))
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    public void testRegisterStudent() throws Exception {
        StudentDto student = new StudentDto(1L, "John Doe", new ArrayList<>(), new ArrayList<>());

        Mockito.when(studentService.registerStudent(any(StudentDto.class))).thenReturn(student);

        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.registrationId").value(1L))
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    public void testUpdateStudent() throws Exception {
        StudentDto student = new StudentDto(1L, "John Doe", new ArrayList<>(), new ArrayList<>());

        Mockito.when(studentService.updateStudent(anyLong(), any(StudentDto.class))).thenReturn(student);

        mockMvc.perform(put("/students/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.registrationId").value(1L))
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    public void testDeleteStudentById() throws Exception {
        mockMvc.perform(delete("/students/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string("Student with registrationId 1 deleted successfully...!!!"));
    }
}
