package com.example.demo.dao;

import com.example.demo.model.Student;

import java.util.List;

public interface StudentDao {
    void insertStudent(Student student);
    void batchInsertStudent(List<Student> studentList);

    void deleteByStudentId(Integer studentId);
    List<Student> getAllStudents();
    Student getByStudentId(Integer studentId);

}
