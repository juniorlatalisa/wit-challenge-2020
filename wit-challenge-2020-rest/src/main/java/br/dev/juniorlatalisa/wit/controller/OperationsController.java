package br.dev.juniorlatalisa.wit.controller;

import java.math.BigDecimal;
import java.math.MathContext;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OperationsController {

	@GetMapping("/sum")
	public BigDecimal add(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
		return a.add(b, MathContext.DECIMAL128);
	}

	@GetMapping("/min")
	public BigDecimal subtract(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
		return a.subtract(b, MathContext.DECIMAL128);
	}

	@GetMapping("/div")
	public BigDecimal divide(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
		return a.divide(b, MathContext.DECIMAL128);
	}

	@GetMapping("/mult")
	public BigDecimal times(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
		return a.multiply(b, MathContext.DECIMAL128);
	}
}