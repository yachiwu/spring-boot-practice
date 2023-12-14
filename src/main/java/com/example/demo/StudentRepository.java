package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student,Integer> {
    Optional<Student> findByName(String name);
    Optional<Student> findByIdAndName(Integer id,String name);
}
