package com.example.demo.controllers;

import java.util.Collections;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.business.PrimeGenerator;

@RestController
public class PrimeController {

	@GetMapping("/primes")
	public Map<String, Object> get_primes(
			@RequestParam(value = "limit", defaultValue = "100") long limit
		) {
		int integerLimit;
		if (limit > Integer.MAX_VALUE) {
			integerLimit = Integer.MAX_VALUE - 1;
		} else integerLimit = (int) limit;
		PrimeGenerator.main(integerLimit);
		return Collections.singletonMap("message", "Done!");
	}

}