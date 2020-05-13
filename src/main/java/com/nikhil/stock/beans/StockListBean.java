package com.nikhil.stock.beans;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class StockListBean {
	private Map<String,String> stockListMapping = new HashMap<String,String>();
	
	@Bean
	StockListBean setStockListMapping(){
		StockListBean stockListBean = new StockListBean();
		String fileName = "MyFile.txt";

		//read file into stream, try-with-resources
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			this.stockListMapping = stream
			.map(s -> s.split("="))
	        .collect(Collectors.toMap(a -> a[0], a -> a[1]));

		} catch (IOException e) {
			e.printStackTrace();
		}
		return stockListBean;
	}

	public Map<String, String> getStockListMapping() {
		return this.stockListMapping;
	}
	
	public void modifyStockListMapping(Map<String, String> stockListMapping){
		System.out.println("modifying----");
		this.stockListMapping = stockListMapping;
	}
}
