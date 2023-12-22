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
    @Test
    public void insertStudent (){
        Student student = new Student();
        student.setName("inserttest");

        // 模擬 呼叫StudentDao 的 insertStudent 方法
        Mockito.when(studentDao.insertStudent(Mockito.any())).thenReturn(100);

        Integer id = studentService.insertStudent(student);

        assertNotNull(id);
        assertEquals(100, id);
    }
    @Test
    public void updateStudentTest() {

        Student studentToUpdate = new Student();
        studentToUpdate.setId(1);
        studentToUpdate.setName("Updated Name");

        // 模擬 呼叫StudentDao 的 updateStudent 方法
        Mockito.doNothing().when(studentDao).updateStudent(Mockito.any(Student.class));
        studentService.updateStudent(studentToUpdate);

        // 驗證是否調用了studentDao.updateStudent()方法，且傳遞的參数是預期的studentToUpdate
        Mockito.verify(studentDao, Mockito.times(1)).updateStudent(studentToUpdate);
    }

}