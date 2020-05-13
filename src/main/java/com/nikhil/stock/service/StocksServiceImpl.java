package com.nikhil.stock.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nikhil.model.GetStockListMappingResponse;
import com.nikhil.stock.beans.StockListBean;

@Service
public class StocksServiceImpl implements IStockService{
     @Autowired
     StockListBean stockListBean;

	@Override
	public GetStockListMappingResponse getStockListMapping(String mobileNumber) {
		Map<String,String> stockListMapping = stockListBean.getStockListMapping();
		
		GetStockListMappingResponse getStockListMappingResponse = new GetStockListMappingResponse();
		getStockListMappingResponse.setStockList(stockListMapping.get(mobileNumber));
		
		return getStockListMappingResponse;
	}

	@Override
	public String addStockListMapping(String mobileNumber, String stockId) {
		Map<String,String> stockListMapping = stockListBean.getStockListMapping();
		
		String s="";

		
		for (String name: stockListMapping.keySet()){
            String key = name.toString();
            String value = stockListMapping.get(name).toString();
            if(stockListMapping.containsKey(mobileNumber)){
            	value+=","+stockId;
            	stockListMapping.put(key, value);
            }
    			
    		
            s+=key + "=" + value;
            } 	
		
		if(!stockListMapping.containsKey(mobileNumber)){
			s+="\n"+mobileNumber + "=" + stockId;
			stockListMapping.put(mobileNumber, stockId);
		}
			
		
		stockListBean.modifyStockListMapping(stockListMapping);		
		
		try {
            FileWriter writer = new FileWriter("MyFile.txt", false);
            writer.write(s);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }		
		return null;
	}
	
	

}
