package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentDao studentDao;

    @Override
    public Integer insertStudent(Student student) {
        return studentDao.insertStudent(student);
    }

    @Override
    public void updateStudent(Student student) {
        studentDao.updateStudent(student);
    }

    @Override
    public void batchInsertStudent(List<Student> studentList) {
        studentDao.batchInsertStudent(studentList);
    }

    @Override
    public void deleteByStudentId(Integer studentId) {
        studentDao.deleteByStudentId(studentId);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();

    }

    @Override
    public Student getByStudentId(Integer studentId) {
        return studentDao.getByStudentId(studentId);
    }
}
