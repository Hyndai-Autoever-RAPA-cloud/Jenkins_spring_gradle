package com.example.jenkinsgradle;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {
    private final Calculator calculator;

    public CalculatorController(Calculator calculator) {
        this.calculator = calculator;
    }

    @GetMapping("/")
    public String index() {
        return "health check";
    }

    @GetMapping("/add")
    public String add(@RequestParam int num1, @RequestParam int num2) {
        return String.valueOf(calculator.addition(num1, num2));
    }

    @GetMapping("/sub")
    public String sub(@RequestParam int num1, @RequestParam int num2) {
        return String.valueOf(calculator.subtraction(num1, num2));
    }

    @GetMapping("/mul")
    public String mul(@RequestParam int num1, @RequestParam int num2) {
        return String.valueOf(calculator.multiplication(num1, num2));
    }
}
