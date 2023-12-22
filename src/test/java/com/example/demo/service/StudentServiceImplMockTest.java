package com.example.demo.service;

import com.example.demo.dao.StudentDao;
import com.example.demo.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StudentServiceImplMockTest {
    @Autowired
    private StudentService studentService;

    @MockBean
    private StudentDao studentDao;
    @BeforeEach
    public void beforeEach(){
        Student mockStudent = new Student();
        mockStudent.setId(100);
        mockStudent.setName("I am mock");
        Mockito.when(studentDao.getByStudentId(Mockito.any())).thenReturn(mockStudent);
    }
    @Test
    public void getByStudentId (){
        Student student = studentService.getByStudentId(3);
        assertNotNull(student);
        assertEquals(100,student.getId());
        assertEquals("I am mock",student.getName());
    }

    @Test
    public void getByStudentId2 (){
        Student student = studentService.getByStudentId(2);
        assertNotNull(student);
        assertEquals(100,student.getId());
        assertEquals("I am mock",student.getName());
    }

}