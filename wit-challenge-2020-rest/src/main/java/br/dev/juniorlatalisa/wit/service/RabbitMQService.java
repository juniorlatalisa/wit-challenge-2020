package br.dev.juniorlatalisa.wit.service;

import java.math.BigDecimal;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.dev.juniorlatalisa.wit.component.RabbitMQComponent;
import br.dev.juniorlatalisa.wit.model.OperationRequest;
import br.dev.juniorlatalisa.wit.model.OperationResponse;

@Service
public class RabbitMQService {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public OperationResponse send(OperationRequest request) {
		rabbitTemplate.convertAndSend(RabbitMQComponent.QUEUE_NAME, request);
		return new OperationResponse(BigDecimal.TEN);
	}
}
