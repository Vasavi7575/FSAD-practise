package com.klu.service;

import org.springframework.stereotype.Service;

@Service
public class CalciService {
	public int add(int a, int b) {
		return a + b;
	}
	public int subtract(int a, int b) {
		return a - b;
	}
	public int multiply(int a, int b) {
		return a * b;
	}
	public double divide(int a, int b) {
		if(b == 0) {
			throw new ArithmeticException("Divison is not allowed");
		}
		return (double) a / b;
	}
	public int modulo(int a, int b) {
		return a % b;
	}

}
