package br.com.ambev.order.infrastructure.event;

import br.com.ambev.order.domain.model.OrderRequest;
import br.com.ambev.order.domain.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
public class KafkaOrderConsumer {

    private final OrderService orderService;
    private final ObjectMapper objectMapper;

    @KafkaListener(
            topics = {"order-create-topic"},
            groupId = "order-group"
    )
    public void consumeMessage(String event, ConsumerRecord<String, OrderRequest> record, Acknowledgment ack) throws JsonProcessingException {
        try {
            System.out.println("Perdido recebido: " + event);
            OrderRequest orderRequest = this.objectMapper.readValue(event, OrderRequest.class);
            Optional order = this.orderService.findByOrderNumber(orderRequest.getOrderNumber());
            if (order.isPresent()) {
                System.out.println("O pedido " + orderRequest.getOrderNumber() + " ja existe");
                ack.acknowledge();
            } else {
                orderService.processOrder(orderRequest);
                System.out.println("Pedido processado!");
                ack.acknowledge();
            }
        } catch (Exception ex) {
            System.out.println("Ocorreu um erro ao processar o pedido " + ex.getMessage());
            //TODO "Implementar uma DLT ou salvar no banco com status FAIL";
            throw ex;
        }
    }
}
