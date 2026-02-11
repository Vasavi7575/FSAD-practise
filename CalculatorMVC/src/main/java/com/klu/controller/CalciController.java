package com.klu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klu.service.CalciService;

@RestController
@RequestMapping("/calculator")
public class CalciController {

    @Autowired
    private CalciService service;

    // ADDITION - RequestParam
    // URL: http://localhost:8080/calculator/add?a=10&b=5
    @RequestMapping("/add")
    public int add(@RequestParam int a, @RequestParam int b) {
        return service.add(a, b);
    }

    // SUBTRACTION - PathVariable
    // URL: http://localhost:8080/calculator/subtract/20/5
    @RequestMapping("/subtract/{a}/{b}")
    public int subtract(@PathVariable int a, @PathVariable int b) {
        return service.subtract(a, b);
    }

    // MULTIPLICATION - PathVariable
    // URL: http://localhost:8080/calculator/multiply/20/5
    @RequestMapping("/multiply/{a}/{b}")
    public int multiply(@PathVariable int a, @PathVariable int b) {
        return service.multiply(a, b);
    }

    // DIVIDE - PathVariable
    // URL: http://localhost:8080/calculator/divide/20/5
    @RequestMapping("/divide/{a}/{b}")
    public double divide(@PathVariable int a, @PathVariable int b) {
        return service.divide(a, b);
    }

    // MODULO - PathVariable
    // URL: http://localhost:8080/calculator/modulo/20/5
    @RequestMapping("/modulo/{a}/{b}")
    public int modulo(@PathVariable int a, @PathVariable int b) {
        return service.modulo(a, b);
    }
}
