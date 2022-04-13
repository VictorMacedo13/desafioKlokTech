package com.example.adesao.rabbitMQ;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;


@Component
public class RabbitMQConection {
	
	private static final String NOME_EXCHANGE = "amq.direct";
	
	private AmqpAdmin amqpAdmin;
	
	public RabbitMQConection(AmqpAdmin amqpAdmin) {
		this.amqpAdmin = amqpAdmin;
	}
	
	private Queue fila(String nomeFila) {
		return new Queue(nomeFila, true, false, false);
	}
	
	private DirectExchange trocaDireta(){
		return new DirectExchange(NOME_EXCHANGE);
	}
	
	private Binding relacionamento(Queue fila, DirectExchange troca) {
		return new Binding(fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null);
	}
	
	@PostConstruct
	private void adiciona() {
		Queue filaCobranca = this.fila(RabbitMQConstantes.FILA_COBRANCA);
		Queue filaPagamento = this.fila(RabbitMQConstantes.FILA_PAGAMENTO);
		
		DirectExchange troca = this.trocaDireta();
		
		Binding ligacaoCobranca = this.relacionamento(filaCobranca, troca);
		Binding ligacaoPagamento = this.relacionamento(filaPagamento, troca);
		
		this.amqpAdmin.declareQueue(filaCobranca);
		this.amqpAdmin.declareQueue(filaPagamento);
		
		this.amqpAdmin.declareExchange(troca);
		
		this.amqpAdmin.declareBinding(ligacaoCobranca);
		this.amqpAdmin.declareBinding(ligacaoPagamento);
		
	}

	
}
