package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private StudentService studentService;
    @PostMapping("/students")
    public String insert(@RequestBody Student student){
            studentService.insertStudent(student);
            return "執行insert sql";
    }
    @PostMapping("/students/batch")
    public String insertList(@RequestBody List<Student> studentList){
        studentService.batchInsertStudent(studentList);
        return "執行一批insert sql";
    }
    @DeleteMapping("/students/{studentId}")
    public String delete(@PathVariable Integer studentId){
        studentService.deleteByStudentId(studentId);
        return "執行delete sql";
    }
    @GetMapping("students")
    public List<Student> select(){
        List<Student> studentList = studentService.getAllStudents();
        return studentList;
    }
    @GetMapping("students/{studentId}")
    public Student selectStudent(@PathVariable Integer studentId){
        return studentService.getByStudentId(studentId);
    }
}
