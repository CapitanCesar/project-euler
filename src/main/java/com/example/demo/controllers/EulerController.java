package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.business.*;
import com.example.demo.records.Euler;

@RestController
public class EulerController {

	@GetMapping("/euler/{id}")
	public Euler getEuler(
		@PathVariable(value = "id") int id,
		@RequestParam(value = "isTest", defaultValue = "false") boolean isTest
	) {
		switch (id) {
			case 0: return Euler0.main(isTest);
			case 1: return Euler01.main(isTest);
			case 2: return Euler02.main(isTest);
			case 3: return Euler03.main(isTest);
			case 4: return Euler04.main(isTest);
			case 5: return Euler05.main(isTest);
			case 6: return Euler06.main(isTest);
			case 7: return Euler07.main(isTest);
			case 8: return Euler08.main(isTest);
			case 9: return Euler09.main(isTest);
			case 10: return Euler10.main(isTest);
			case 11: return Euler11.main(isTest);
			case 12: return Euler12.main(isTest);
			case 13: return Euler13.main(isTest);
			case 14: return Euler14.main(isTest);
			case 15: return Euler15.main(isTest);
			case 16: return Euler16.main(isTest);
			case 17: return Euler17.main(isTest);
			case 18: return Euler18.main(isTest);
			case 19: return Euler19.main(isTest);
			case 20: return Euler20.main(isTest);
			case 21: return Euler21.main(isTest);
			case 22: return Euler22.main(isTest);
			case 23: return Euler23.main(isTest);
			case 24: return Euler24.main(isTest);
			case 25: return Euler25.main(isTest);
			case 26: return Euler26.main(isTest);
			case 27: return Euler27.main(isTest);
			case 28: return Euler28.main(isTest);
			case 29: return Euler29.main(isTest);
			case 30: return Euler30.main(isTest);
			case 31: return Euler31.main(isTest);
			case 32: return Euler32.main(isTest);
			case 33: return Euler33.main(isTest);
			case 34: return Euler34.main(isTest);
			case 35: return Euler35.main(isTest);
			case 36: return Euler36.main(isTest);
			case 37: return Euler37.main(isTest);
			case 38: return Euler38.main(isTest);
			default: return new Euler("Invalid Euler ID", "");
		}
	}
}
