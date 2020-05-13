package com.nikhil.stock.service;

import com.nikhil.model.GetStockListMappingResponse;

public interface IStockService {
	public String addStockListMapping(String mobileNumber, String stockId);
	GetStockListMappingResponse getStockListMapping(String mobileNumber);
}
