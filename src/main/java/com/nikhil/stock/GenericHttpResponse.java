package com.nikhil.stock;

public class GenericHttpResponse {
private int statusCode;
private String responseString;
public int getStatusCode() {
	return statusCode;
}
public void setStatusCode(int statusCode) {
	this.statusCode = statusCode;
}
public String getResponseString() {
	return responseString;
}
public void setResponseString(String responseString) {
	this.responseString = responseString;
}
@Override
public String toString() {
	return "GenericHttpResponse [statusCode=" + statusCode
			+ ", responseString=" + responseString + "]";
}




}
