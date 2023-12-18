package com.example.demo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    @Disabled
    @Test
    public void add(){
        Calculator calculator = new Calculator();
        int result = calculator.add(1,2);
        assertEquals(3,result,"add has problem");
        assertTrue(result>1);
    }

    @DisplayName("測試除法問題")
    @Test
    public void divide(){
        Calculator calculator = new Calculator();
        assertThrows(ArithmeticException.class,() -> {
            calculator.divide(1,0);
        });

    }
}