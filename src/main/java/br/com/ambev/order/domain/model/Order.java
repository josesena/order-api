package br.com.ambev.order.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Order {

    @Id
    private String id;
    private long orderNumber;
    private List<Product> products;
    private BigDecimal totalPrice;
    private String status;
}
