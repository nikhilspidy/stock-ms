package com.nikhil.stock.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StockController {

	@RequestMapping("/")
	public String index() {
		try {
            FileWriter writer = new FileWriter("MyFile.txt", true);
            writer.write("Hello World");
            writer.write("\r\n");   // write new line
            writer.write("Good Bye!");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }		
		
		String s="";
		 try {
	            FileReader reader = new FileReader("MyFile.txt");
	            int character;
	 
	            while ((character = reader.read()) != -1) {
	                s+=(char) character;
	            }
	            reader.close();
	 
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		
		return s;
	}

}