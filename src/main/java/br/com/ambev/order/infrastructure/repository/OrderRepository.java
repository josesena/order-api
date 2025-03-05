package br.com.ambev.order.infrastructure.repository;


import br.com.ambev.order.domain.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OrderRepository extends MongoRepository<Order, String> {

    Optional<Order> findByOrderNumber(long orderNumber);
}
