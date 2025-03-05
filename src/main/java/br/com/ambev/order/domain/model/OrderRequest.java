package br.com.ambev.order.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderRequest {

    private long orderNumber;
    private List<Product> products;
}
