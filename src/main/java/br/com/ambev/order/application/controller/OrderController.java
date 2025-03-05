package br.com.ambev.order.application.controller;

import br.com.ambev.order.domain.model.Order;
import br.com.ambev.order.domain.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    @GetMapping({"/{orderNumber}"})
    public ResponseEntity<Optional<Order>> getOrderByNumber(@PathVariable long orderNumber) {
        Optional response = this.orderService.findByOrderNumber(orderNumber);
        ResponseEntity var10000;
        if (response.isPresent()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status((HttpStatusCode) HttpStatus.NO_CONTENT).build();
        }
    }
}
