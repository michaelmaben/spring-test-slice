package com.jeevanmaben.test;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.anyLong;

@WebMvcTest
@RequiredArgsConstructor
public class StudentControllerTest {

    @Autowired
    private StudentController studentController;

    @MockBean
    private StudentService studentService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getStudent() throws Exception{
        //given
        BDDMockito.given(studentService.getStudentById(anyLong())).willReturn(
            Student.builder()
                    .name("Jeevan")
                    .grade(100)
                    .id(123l)
                    .build()
        );
        //when //then
        mockMvc.perform(MockMvcRequestBuilders.get("/students/123"))
                    .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getMissingStudent() throws Exception{
        //given
        BDDMockito.given(studentService.getStudentById(anyLong())).willThrow(StudentNotFoundException.class);
        //when //then
        mockMvc.perform(MockMvcRequestBuilders.get("/students/123"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

}
