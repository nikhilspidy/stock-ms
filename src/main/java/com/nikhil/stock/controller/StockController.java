package com.nikhil.stock.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StockController {

	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

}