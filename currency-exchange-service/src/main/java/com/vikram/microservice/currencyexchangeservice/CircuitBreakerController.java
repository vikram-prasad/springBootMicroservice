package com.vikram.microservice.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
public class CircuitBreakerController {
	Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	@GetMapping("/sample-api")
	//@Retry(name="sample-api",fallbackMethod = "hardCodedResponse")
	//@CircuitBreaker(name="sample-api",fallbackMethod = "hardCodedResponse")
	//@RateLimiter(name="default")//10s to have 10000 calls to sample api
	@Bulkhead(name="default")
	public String sampleApi() {
		logger.info("Sampleapi call received");
		/*
		 * ResponseEntity<String> forEntity = new
		 * RestTemplate().getForEntity("localhost:8080/dummy-url", String.class); return
		 * forEntity.getBody();
		 */
		return "Sample API";
	}
	
	private String hardCodedResponse(Exception ex) {
		return "Fallback Response";
	}
}
