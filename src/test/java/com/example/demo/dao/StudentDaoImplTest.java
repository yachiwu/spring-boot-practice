package com.example.demo.dao;

import com.example.demo.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StudentDaoImplTest {
    @Autowired
    private StudentDao studentDao;

    @Test
    public void getByStudentId(){
        Student student = studentDao.getByStudentId(3);
        assertNotNull(student);
        assertEquals("Judy",student.getName());
    }
    //@Transactional 可以roll back 針對db的操作
    @Test
    @Transactional
    public void deleteByStudentId(){
        studentDao.deleteByStudentId(3);
        Student student = studentDao.getByStudentId(3);
        assertNull(student);
    }
    @Test
    @Transactional
    public void insertStudent(){
        Student student = new Student();
        student.setName("kevin");
        Integer studentId = studentDao.insertStudent(student);
        Student result = studentDao.getByStudentId(studentId);
        assertNotNull(result);
        assertEquals("kevin",result.getName());
    }

    @Test
    @Transactional
    public void updateStudent(){
        Student student = studentDao.getByStudentId(3);
        student.setName("john");
        studentDao.updateStudent(student);
        Student result = studentDao.getByStudentId(3);
        assertNotNull(result);
        assertEquals("john",result.getName());
    }
}