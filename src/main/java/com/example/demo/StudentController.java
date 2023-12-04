package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.PanelUI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StudentController {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @PostMapping("/students")
    public String insert(@RequestBody Student student){
        String sql = "INSERT INTO student(name) VALUES (:studentName)";
        Map<String,Object> map = new HashMap<>();
        map.put("studentName",student.getName());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);
        int id = keyHolder.getKey().intValue();
        System.out.println("mysql 自動生成的id為 "+ id);
        return "執行insert sql";
    }
    @PostMapping("/students/batch")
    public String insertList(@RequestBody List<Student> studentList){
        String sql = "INSERT INTO student(name) VALUES (:studentName)";
        MapSqlParameterSource[] parameterSources = new MapSqlParameterSource[studentList.size()];
        for (int i=0;i<studentList.size();i++){
            Student student = studentList.get(i);
            parameterSources[i] = new MapSqlParameterSource();
            parameterSources[i].addValue("studentName",student.getName());
        }
        namedParameterJdbcTemplate.batchUpdate(sql,parameterSources);
        return "執行一批insert sql";
    }
    @DeleteMapping("/students/{studentId}")
    public String delete(@PathVariable Integer studentId){
        String sql = "DELETE FROM  student WHERE id=:studentId";
        Map<String,Object> map = new HashMap<>();
        map.put("studentId",studentId);
        namedParameterJdbcTemplate.update(sql,map);
        return "執行delete sql";
    }
}
