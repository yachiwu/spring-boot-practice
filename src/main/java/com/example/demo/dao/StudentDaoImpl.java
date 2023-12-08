package com.example.demo.dao;

import com.example.demo.model.Student;
import com.example.demo.mapper.StudentRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StudentDaoImpl implements StudentDao{
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public void insertStudent(Student student) {
        String sql = "INSERT INTO student(name) VALUES (:studentName)";
        Map<String,Object> map = new HashMap<>();
        map.put("studentName",student.getName());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);
        int id = keyHolder.getKey().intValue();
        System.out.println("mysql 自動生成的id為 "+ id);
    }

    @Override
    public void batchInsertStudent(List<Student> studentList) {
        String sql = "INSERT INTO student(name) VALUES (:studentName)";
        MapSqlParameterSource[] parameterSources = new MapSqlParameterSource[studentList.size()];
        for (int i=0;i<studentList.size();i++){
            Student student = studentList.get(i);
            parameterSources[i] = new MapSqlParameterSource();
            parameterSources[i].addValue("studentName",student.getName());
        }
        namedParameterJdbcTemplate.batchUpdate(sql,parameterSources);
    }

    @Override
    public void deleteByStudentId(Integer studentId) {
        String sql = "DELETE FROM student WHERE id=:studentId";
        Map<String,Object> map = new HashMap<>();
        map.put("studentId",studentId);
        namedParameterJdbcTemplate.update(sql,map);
    }

    @Override
    public List<Student> getAllStudents() {
        String sql = "SELECT id,name FROM student";
        Map<String,Object> map = new HashMap<>();
        List<Student> studentList= namedParameterJdbcTemplate.query(sql,map,new StudentRowMapper());
        return studentList;
    }

    @Override
    public Student getByStudentId(Integer studentId) {
        String countSql = "SELECT count(*) FROM student";
        Map<String,Object> countMap = new HashMap<>();
        Integer count = namedParameterJdbcTemplate.queryForObject(countSql,countMap,Integer.class);
        System.out.println("student table count is "+ count);
        String sql = "SELECT id,name FROM student where id=:studentId";
        Map<String,Object> map = new HashMap<>();
        map.put("studentId",studentId);
        List<Student> studentList= namedParameterJdbcTemplate.query(sql,map,new StudentRowMapper());
        if (studentList.size() >0){
            return studentList.get(0);
        }else{
            return null;
        }
    }


}
