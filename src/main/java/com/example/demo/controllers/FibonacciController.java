package com.example.demo.controllers;

import java.util.Collections;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.business.FibonacciGenerator;

@RestController
public class FibonacciController {

	@GetMapping("/fibonacci")
	public Map<String, Object> get_fibonacci_numbers(
			@RequestParam(value = "limit", defaultValue = "4000000") int limit
		) {
		FibonacciGenerator.main(limit);
		return Collections.singletonMap("message", "Done!");
	}

}