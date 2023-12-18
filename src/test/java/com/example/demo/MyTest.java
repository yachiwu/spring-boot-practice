package com.example.demo;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

public class MyTest {
    @BeforeEach
    public void beforeEach(){
        System.out.println("執行beforeEach");
    }
    @AfterEach
    public void afterEach(){
        System.out.println("執行afterEach");
    }
    @BeforeAll
    public static void beforeAll(){
        System.out.println("執行beforeAll");
    }

    @AfterAll
    public static void afterAll(){
        System.out.println("執行afterAll");
    }
    @Test
    public void test1(){
        System.out.println("執行test1");
    }

    @Test
    public void test2(){
        System.out.println("執行test2");
    }
}
