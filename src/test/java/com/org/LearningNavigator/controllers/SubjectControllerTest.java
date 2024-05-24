package com.org.LearningNavigator.controllers;

import com.org.LearningNavigator.dto.SubjectDto;
import com.org.LearningNavigator.services.SubjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class SubjectControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SubjectService subjectService;

    @InjectMocks
    private SubjectController subjectController;

    private SubjectDto subjectDto;
    private List<SubjectDto> subjectDtoList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(subjectController).build();

        subjectDto = new SubjectDto(1L, "Mathematics", null);
        subjectDtoList = Arrays.asList(subjectDto);
    }

    /*The differences when we use @WebMvcTest annotation
        @WebMvcTest(SubjectController.class)
        class SubjectControllerTest {

            @Autowired
            private MockMvc mockMvc;

            @MockBean
            private SubjectService subjectService;

            private SubjectDto subjectDto;

            @BeforeEach
            void setUp() {
                subjectDto = new SubjectDto();
                subjectDto.setSubjectId(1L);
                subjectDto.setName("Mathematics");
                subjectDto.setStudents(Arrays.asList());  // Assuming the students list is empty for this test
            }

    * */

    @Test
    void getSubjects() throws Exception {
        when(subjectService.getSubjects()).thenReturn(subjectDtoList);

        mockMvc.perform(get("/subjects"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].subjectId").value(subjectDto.getSubjectId()))
                .andExpect(jsonPath("$[0].name").value(subjectDto.getName()));
    }

    @Test
    void getSubjectById() throws Exception {
        when(subjectService.getSubjectById(anyLong())).thenReturn(subjectDto);

        mockMvc.perform(get("/subjects/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.subjectId").value(subjectDto.getSubjectId()))
                .andExpect(jsonPath("$.name").value(subjectDto.getName()));
    }

    @Test
    void createSubject() throws Exception {
        when(subjectService.createSubject(any(SubjectDto.class))).thenReturn(subjectDto);

        mockMvc.perform(post("/subjects")
                        .contentType("application/json")
                        .content("{\"subjectId\":1,\"name\":\"Mathematics\",\"students\":null}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.subjectId").value(subjectDto.getSubjectId()))
                .andExpect(jsonPath("$.name").value(subjectDto.getName()));
    }

    @Test
    void updateSubject() throws Exception {
        when(subjectService.updateSubject(anyLong(), any(SubjectDto.class))).thenReturn(subjectDto);

        mockMvc.perform(put("/subjects/1")
                        .contentType("application/json")
                        .content("{\"subjectId\":1,\"name\":\"Mathematics\",\"students\":null}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.subjectId").value(subjectDto.getSubjectId()))
                .andExpect(jsonPath("$.name").value(subjectDto.getName()));
    }

    @Test
    void deleteSubjectById() throws Exception {
        mockMvc.perform(delete("/subjects/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Subject with subjectId 1 deleted successfully...!!!"));
    }

    @Test
    void enrollStudentForSubject() throws Exception {
        when(subjectService.enrollStudent(anyLong(), anyLong())).thenReturn(subjectDto);

        mockMvc.perform(put("/subjects/1/enrollStudent/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.subjectId").value(subjectDto.getSubjectId()))
                .andExpect(jsonPath("$.name").value(subjectDto.getName()));
    }
}
