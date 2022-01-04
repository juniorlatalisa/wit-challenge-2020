package br.dev.juniorlatalisa.wit.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Service;

import br.dev.juniorlatalisa.wit.component.RabbitMQComponent;
import br.dev.juniorlatalisa.wit.model.OperationRequest;
import br.dev.juniorlatalisa.wit.model.OperationResponse;

@Service
public class RabbitMQService {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Value("${juniorlatalisa.wit.response.id}")
	private String responseId;

	public ResponseEntity<OperationResponse> send(OperationRequest request) {
		var entity = (OperationResponse) rabbitTemplate.convertSendAndReceive(RabbitMQComponent.QUEUE_NAME, request);
		BodyBuilder builder;
		if (entity == null) {
			builder = ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT);
			entity = new OperationResponse(null);
		} else {
			builder = ResponseEntity.ok();
		}
		return builder.header(responseId, request.getId()).body(entity);
	}
}