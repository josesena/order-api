package br.com.ambev.order.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private long orderNumber;
    private BigDecimal totalPrice;
    private List<Product> products;
    private String status;

}
