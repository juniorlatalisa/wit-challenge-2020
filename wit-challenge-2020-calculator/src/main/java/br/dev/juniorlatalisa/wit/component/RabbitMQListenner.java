package br.dev.juniorlatalisa.wit.component;

import java.math.BigDecimal;
import java.math.MathContext;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import br.dev.juniorlatalisa.wit.model.OperationRequest;
import br.dev.juniorlatalisa.wit.model.OperationResponse;
import br.dev.juniorlatalisa.wit.utils.LogUtils;

@Component
public class RabbitMQListenner {

	@RabbitListener(queues = RabbitMQComponent.QUEUE_NAME)
	public OperationResponse onRequest(OperationRequest request) {
		LogUtils.logger.info(String.format("Process ID [%s] from Queue [%s]", //
				request.getId(), RabbitMQComponent.QUEUE_NAME));
		return new OperationResponse(calculate(request, MathContext.DECIMAL128));
	}

	public BigDecimal calculate(OperationRequest request, MathContext mc) {
		switch (request.getOperation()) {
		case ADDITION:
			return request.getValueA().add(request.getValueB(), mc);
		case DIVISION:
			return request.getValueA().divide(request.getValueB(), mc);
		case MULTIPLICATION:
			return request.getValueA().multiply(request.getValueB(), mc);
		case SUBTRACTION:
			return request.getValueA().subtract(request.getValueB(), mc);
		default:
			throw new IllegalArgumentException("Invalid operation");
		}
	}
}
