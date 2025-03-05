package br.com.ambev.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;


@SpringBootApplication
public class OrderApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderApiApplication.class, args);
	}

}
