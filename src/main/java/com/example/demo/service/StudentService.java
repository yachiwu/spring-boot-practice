package com.example.demo.service;

import com.example.demo.model.Student;

import java.util.List;

public interface StudentService {
    void insertStudent(Student student);
    void batchInsertStudent(List<Student> studentList);
    void updateStudent(Student student);

    void deleteByStudentId(Integer studentId);
    List<Student> getAllStudents();
    Student getByStudentId(Integer studentId);
}
