package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @RequestMapping("/test1")
    public String test1(){
        System.out.println("執行test1 方法");
        return "hello test1";
    }

    @RequestMapping("/test2")
    public String test2() {
        System.out.println("執行test2 方法");
        return "hello test2";
    }

}
