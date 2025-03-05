package br.com.ambev.order.infrastructure.event;

import br.com.ambev.order.domain.model.OrderResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@EnableKafka
@AllArgsConstructor
@Component
public class KafkaOrderProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void sendMessage(OrderResponse order) throws JsonProcessingException {
        String message = this.objectMapper.writeValueAsString(order);
        kafkaTemplate.send("order-processing-topic", message);
        System.out.println("Pedido calculado e enviado para o topic");
    }
}
