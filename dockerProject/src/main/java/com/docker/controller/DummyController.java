package com.docker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyController {
	
	@GetMapping("/")
	public String getData() {
		return "Hello, Dockerized Spring Boot!";          
	}
}
