package com.consume.bike.consumebike.com.consume.bike.consumebike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableScheduling
public class ConsumebikeApplication implements CommandLineRunner {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient webClient;

	public static void main(String[] args) {
		SpringApplication.run(ConsumebikeApplication.class, args);
	}

	@Override
	@Scheduled(fixedRate = 1000)
	public void run(String... args) throws Exception {
		
		
		//String susana= this.restTemplate.getForObject("http://localhost:8080/api/v1/bikes",String.class);
		
		  this.webClient = WebClient.create("http://localhost:8080/api/v1/bikes");
	      Mono<String> result = this.webClient.get()
	                                     .retrieve()
	                                     .bodyToMono(String.class);
	      String response = result.block();
	      System.out.println(response);
		
	}

}
