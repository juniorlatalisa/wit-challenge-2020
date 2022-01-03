package br.dev.juniorlatalisa.wit.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.dev.juniorlatalisa.wit.model.Operation;
import br.dev.juniorlatalisa.wit.model.OperationRequest;
import br.dev.juniorlatalisa.wit.model.OperationResponse;
import br.dev.juniorlatalisa.wit.service.RabbitMQService;

@RestController
public class OperationsController {

	@Autowired
	private RabbitMQService queues;

	@GetMapping("/sum")
	public ResponseEntity<OperationResponse> add(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
		return queues.send(new OperationRequest(a, b, Operation.ADDITION));
	}

	@GetMapping("/min")
	public ResponseEntity<OperationResponse> subtract(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
		return queues.send(new OperationRequest(a, b, Operation.SUBTRACTION));
	}

	@GetMapping("/div")
	public ResponseEntity<OperationResponse> divide(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
		return queues.send(new OperationRequest(a, b, Operation.DIVISION));
	}

	@GetMapping("/mult")
	public ResponseEntity<OperationResponse> times(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
		return queues.send(new OperationRequest(a, b, Operation.MULTIPLICATION));
	}
}