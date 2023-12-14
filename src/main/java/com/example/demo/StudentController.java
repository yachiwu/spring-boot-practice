package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    @PostMapping("/students")
    public String insert(@RequestBody Student student){
        studentRepository.save(student);
        return "執行資料庫create操作";
    }

    @PutMapping("/students/{studentId}")
    public String update(@PathVariable Integer studentId, @RequestBody Student student){
        Student s  =  studentRepository.findById(studentId).orElse(null);
        if(s != null){
            s.setName(student.getName());
            studentRepository.save(s);
            return "執行資料庫update操作";
        }
        else{
            return "執行資料庫update操作 失敗! 數據不存在";
        }

    }

    @DeleteMapping("/students/{studentId}")
    public String delete(@PathVariable Integer studentId){
        studentRepository.deleteById(studentId);
        return "執行資料庫delete操作";
    }
    @GetMapping("/students/{studentId}")
    public Student read(@PathVariable Integer studentId){
        Student student = studentRepository.findById(studentId).orElse(null);
        return student;
    }
    @GetMapping("/students")
    public Student getStudent(
            @RequestParam(name = "id", required = false) Integer studentId,
            @RequestParam(name="name") String studentName){
        if (studentId != null) {
            Optional<Student> student = studentRepository.findByIdAndName(studentId, studentName);
            if (student.isPresent()) {
                System.out.println("找到");
                return student.get();
            } else {
                System.out.println("根據ID和名字未找到");
                return null;
            }
        } else {
            // Handle the case where 'id' parameter is not provided
            // Query by 'name' only
            Optional<Student> student= studentRepository.findByName(studentName);
            if (student.isPresent()) {
                System.out.println("找到");
                return student.get();
            } else {
                System.out.println("根據ID和名字未找到");
                return null;
            }
        }
    }






}
