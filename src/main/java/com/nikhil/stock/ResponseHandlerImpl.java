package com.nikhil.stock;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;
import org.springframework.http.ResponseEntity;

public class ResponseHandlerImpl implements ResponseHandler<GenericHttpResponse>{

	@Override
	public GenericHttpResponse handleResponse(HttpResponse httpResponse)
			throws ClientProtocolException, IOException {
		
		HttpEntity responseEntity = null;
		GenericHttpResponse genericHttpResponse = new GenericHttpResponse();
		
		try{
			responseEntity = httpResponse.getEntity();
			genericHttpResponse.setStatusCode(httpResponse.getStatusLine().getStatusCode());
			genericHttpResponse.setResponseString(EntityUtils.toString(responseEntity));
			return genericHttpResponse;
		}finally{
			EntityUtils.consume(responseEntity);
		}
		
	}

}
