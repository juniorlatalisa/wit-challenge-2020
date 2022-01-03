package br.dev.juniorlatalisa.wit.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.dev.juniorlatalisa.wit.component.RabbitMQComponent;
import br.dev.juniorlatalisa.wit.model.OperationRequest;
import br.dev.juniorlatalisa.wit.model.OperationResponse;

@Service
public class RabbitMQService {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public ResponseEntity<OperationResponse> send(OperationRequest request) {
		var entity = (OperationResponse) rabbitTemplate.convertSendAndReceive(RabbitMQComponent.QUEUE_NAME, request);
		return entity == null ? ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT)
				.body(new OperationResponse(null))
				: ResponseEntity.ok(entity);
	}
}
