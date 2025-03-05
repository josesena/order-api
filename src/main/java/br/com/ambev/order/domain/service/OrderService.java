package br.com.ambev.order.domain.service;

import br.com.ambev.order.domain.model.Order;
import br.com.ambev.order.domain.model.OrderRequest;
import br.com.ambev.order.domain.model.OrderResponse;
import br.com.ambev.order.domain.model.OrderStatus;
import br.com.ambev.order.domain.model.Product;
import br.com.ambev.order.infrastructure.event.KafkaOrderProducer;
import br.com.ambev.order.infrastructure.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final KafkaOrderProducer kafkaOrderProducer;

    @Transactional
    public void processOrder(OrderRequest request) throws JsonProcessingException {
        Order order = this.createOrder(request);

        try {
            Order saveOrder = orderRepository.save(order);
            System.out.println("Pedido salvo com sucesso! " + saveOrder);
            OrderResponse orderResponse = this.toResponse(saveOrder);
            this.kafkaOrderProducer.sendMessage(orderResponse);
        } catch (Exception ex) {
            System.out.println("Houve uma falha ao processar o pedido " + ex.getMessage());
            throw ex;
        }
    }

    public Order createOrder(OrderRequest request) {
        return Order.builder()
                .orderNumber(request.getOrderNumber())
                .products(request.getProducts()).totalPrice(caculeTotalPrice(request.getProducts()))
                .status(OrderStatus.PROCESSED.name()).build();
    }

    private BigDecimal caculeTotalPrice(List<Product> products) {
        return products.stream()
                .map(p -> p.getPrice().multiply(BigDecimal.valueOf(p.getQuantity()))).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private OrderResponse toResponse(Order saveOrder) {
        return new OrderResponse(saveOrder.getOrderNumber(), saveOrder.getTotalPrice(), saveOrder.getProducts(), saveOrder.getStatus());
    }

    public Optional<Order> findByOrderNumber(long orderNumber) {
        return this.orderRepository.findByOrderNumber(orderNumber);
    }
}
