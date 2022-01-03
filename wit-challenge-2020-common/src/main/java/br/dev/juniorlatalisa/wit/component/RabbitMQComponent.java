package br.dev.juniorlatalisa.wit.component;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQComponent {

	public static final String EXCHANGE_NAME = "amq.direct";
	public static final String QUEUE_NAME = "operations";

	private AmqpAdmin amqpAdmin;

	public RabbitMQComponent(AmqpAdmin amqpAdmin) {
		this.amqpAdmin = amqpAdmin;
	}

	protected Queue fila(String nomeFila) {
		return new Queue(nomeFila, true, false, false);
	}

	protected DirectExchange trocaDireta() {
		return new DirectExchange(EXCHANGE_NAME);
	}

	protected Binding relacionamento(Queue fila, DirectExchange troca) {
		return new Binding(fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null);
	}

	@PostConstruct
	protected void postConstruct() {
		var fila = fila(QUEUE_NAME);
		var declareQueue = amqpAdmin.declareQueue(fila);
		var troca = trocaDireta();
		amqpAdmin.declareExchange(troca);
		var relacionamento = relacionamento(fila, troca);
		amqpAdmin.declareBinding(relacionamento);
		System.out.println(declareQueue);
	}
}
