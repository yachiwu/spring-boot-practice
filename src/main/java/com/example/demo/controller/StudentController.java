package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    private final static Logger log = LoggerFactory.getLogger(StudentController.class);
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private StudentService studentService;
    @PostMapping("/students")
    public ResponseEntity<Student>  insert(@RequestBody Student student){
            Integer studentId = studentService.insertStudent(student);
            Student newStudent = studentService.getByStudentId(studentId);
        return ResponseEntity.status(HttpStatus.CREATED).body(newStudent);
    }
    @PutMapping("/students/{studentId}")
    public ResponseEntity<?> update(@PathVariable Integer studentId,
                                    @RequestBody Student student) {

        student.setId(studentId);
        studentService.updateStudent(student);

        return ResponseEntity.status(HttpStatus.OK).build();
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
    public ResponseEntity<List<Student>> select(){
        List<Student> studentList = studentService.getAllStudents();
        return ResponseEntity.status(HttpStatus.OK).body(studentList);
    }
    @GetMapping("students/{studentId}")
    public ResponseEntity<Student> selectStudent(@PathVariable Integer studentId){
        log.info("get student {}",studentId);
        Student student = studentService.getByStudentId(studentId);
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }
}
