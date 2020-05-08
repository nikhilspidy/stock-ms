package com.nikhil.stock.controller;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.nikhil.stock.GenericHttpResponse;
import com.nikhil.stock.ResponseHandlerImpl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

@CrossOrigin(origins = "*" )
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
	
	@RequestMapping("/getStocksList/{id}")
	public String getStockList(@PathVariable String id) {
		System.setProperty("http.keepAlive", "false"); 
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		//headers.set("Accept-Language", "en-US,en;q=0.8");
		headers.set("Accept","application/json");

		//final String uri = "https://www.nseindia.com/api/search/autocomplete?q="+id;
		final String uri="https://reqres.in/api/users?page=2";
		//String result = restTemplate.getForObject("https://www.nseindia.com/api/search/autocomplete?q={id}", String.class,id);
		String result = restTemplate.getForObject("http://priceapi-aws.moneycontrol.com/pricefeed/bse/equitycash/RI", String.class,2);
return result;
	}
	
	@RequestMapping("/getStocksLists")
	public  void demoGetRESTAPI() throws Exception 
	{
	    DefaultHttpClient httpClient = new DefaultHttpClient();
	    try
	    {
	        //Define a HttpGet request; You can choose between HttpPost, HttpDelete or HttpPut also.
	        //Choice depends on type of method you will be invoking.
	        HttpGet getRequest = new HttpGet("http://priceapi-aws.moneycontrol.com/pricefeed/bse/equitycash/RI");
	         
	        //Set the API media type in http accept header
	        getRequest.addHeader("accept", "application/json");
	        getRequest.addHeader("Content-Type","application/json");
	        getRequest.addHeader("User-Agent","");
	          
	        //Send the request; It will immediately return the response in HttpResponse object
	        HttpResponse response = httpClient.execute(getRequest);
	         
	        //verify the valid error code first
	        int statusCode = response.getStatusLine().getStatusCode();
	        if (statusCode != 200) 
	        {
	            throw new RuntimeException("Failed with HTTP error code : " + statusCode);
	        }
	         
	        //Now pull back the response object
	        HttpEntity httpEntity = response.getEntity();
	        String apiOutput = EntityUtils.toString(httpEntity);
	        
	        System.out.println(apiOutput);
	         
	        //Lets see what we got from API
	        //System.out.println(apiOutput); //<user id="10"><firstName>demo</firstName><lastName>user</lastName></user>
	         
	        //In realtime programming, you will need to convert this http response to some java object to re-use it.
	        //Lets see how to jaxb unmarshal the api response content
	     
	    }
	    finally
	    {
	        //Important: Close the connect
	        httpClient.getConnectionManager().shutdown();
	    }
	}
	
	@RequestMapping("/getStocksLists1")
	public  String demoGetRESTAPI1() throws Exception {
		
		
		//OkHttpClient client = new OkHttpClient();
	    //Request restRequest = new Request.Builder().url(System.getProperty("deployment.url") + "/rest/members").build();
		OkHttpClient client = new OkHttpClient();
				Request request = new Request.Builder()
				  .url("http://priceapi-aws.moneycontrol.com/pricefeed/bse/equitycash/RI")
				  .method("GET", null)
				  .addHeader("Cookie", "ak_bmsc=B5FB92D08F05641591BE2173D9D0BFF8312C8855373600006DEEAD5EAB1BB127~plqBt1Lfan6VdTnYhR/E44SWzihRqqjEZZfFeD0NooDr5qVqmiFYGM4JNmpEAxZ9/roS1ElGrNwRJoc5Ron0KA0NSXpxPUU5gAiuJPnoXMXP88Ep/4gjyb4sFpNkOSGumCD1iWNzhFtjxTRaJ7Momgu8l/afzDB6Q7h7R+pptz6ML3UOXHuKPfRl6uciv9GSCw2KZ8tkOxM8NW5w7bZ8PQw9zKT6NxLyvqj0adcL5uJ7k=")
				  .build();
				Response response = client.newCall(request).execute();
				System.out.println("888888 "+response.body().toString());
				return response.body().toString();
		
		
	}

	@RequestMapping("/getStockDetails/{id}")
	public  String demoGetRESTAPI2(@PathVariable("id") String id) throws Exception {
		CloseableHttpClient closeableHttpClient;
		closeableHttpClient = HttpClients.custom().build();
		GenericHttpResponse genericHttpResponse= new GenericHttpResponse();
		ResponseHandler<GenericHttpResponse> responseHandler = new ResponseHandlerImpl();
		HttpRequestBase httpMethod = new HttpGet("http://priceapi-aws.moneycontrol.com/pricefeed/bse/equitycash/"+id);
		genericHttpResponse = closeableHttpClient.execute(httpMethod,responseHandler);
	System.out.println(genericHttpResponse);
	return genericHttpResponse.getResponseString();
	}
	
}