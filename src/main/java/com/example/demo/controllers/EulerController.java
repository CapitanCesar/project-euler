package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.business.Euler0;
import com.example.demo.business.Euler01;
import com.example.demo.business.Euler02;
import com.example.demo.business.Euler03;
import com.example.demo.business.Euler04;
import com.example.demo.business.Euler05;
import com.example.demo.records.Euler;

@RestController
public class EulerController {

	@GetMapping("/euler/0")
	public Euler get_euler_0(@RequestParam(value = "isTest", defaultValue = "false") boolean isTest) {
		Euler euler = Euler0.main(isTest);
		return euler;
	}

	@GetMapping("/euler/1")
	public Euler get_euler_1(@RequestParam(value = "isTest", defaultValue = "false") boolean isTest) {
		Euler euler = Euler01.main(isTest);
		return euler;
	}

	@GetMapping("/euler/2")
	public Euler get_euler_2(@RequestParam(value = "isTest", defaultValue = "false") boolean isTest) {
		Euler euler = Euler02.main(isTest);
		return euler;
	}

	@GetMapping("/euler/3")
	public Euler get_euler_3(@RequestParam(value = "isTest", defaultValue = "false") boolean isTest) {
		Euler euler = Euler03.main(isTest);
		return euler;
	}

	@GetMapping("/euler/4")
	public Euler get_euler_4(@RequestParam(value = "isTest", defaultValue = "false") boolean isTest) {
		Euler euler = Euler04.main(isTest);
		return euler;
	}

	@GetMapping("/euler/5")
	public Euler get_euler_5(@RequestParam(value = "isTest", defaultValue = "false") boolean isTest) {
		Euler euler = Euler05.main(isTest);
		return euler;
	}
}