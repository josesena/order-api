//package br.com.ambev.order.infrastructure;
//
//import br.com.ambev.order.domain.model.OrderRequest;
//import br.com.ambev.order.domain.model.Product;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.AllArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.kafka.core.KafkaTemplate;
//
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.util.Collections;
//import java.util.Random;
//
//@AllArgsConstructor
//public class OrderInitializer implements CommandLineRunner {
//
//
//    private final KafkaTemplate<String, String> kafkaTemplate;
//    private final ObjectMapper objectMapper;
//
//    @Override
//    public void run(String... args) throws Exception {
//        for(long i = 1L; i < 200001L; ++i) {
//            OrderRequest order = this.orderGenerate(i);
//            this.kafkaTemplate.send("order-create-topic", this.objectMapper.writeValueAsString(order));
//            if (i % 10000L == 0L) {
//                System.out.println(i + " petidos enviado para o kafka...");
//                this.kafkaTemplate.flush();
//            }
//        }
//    }
//
//    private OrderRequest orderGenerate(long i) {
//        Random random = new Random();
//        BigDecimal price = BigDecimal.valueOf(1 + (99 * random.nextDouble()))
//                .setScale(2, RoundingMode.HALF_UP);
//        int quantity = random.nextInt(10) + 1;
//        Product product = new Product("Produto " + i, price, quantity);
//        return new OrderRequest(i, Collections.singletonList(product));
//    }
//}
