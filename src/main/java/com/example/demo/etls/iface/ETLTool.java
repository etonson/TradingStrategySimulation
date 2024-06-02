package com.example.demo.etls.iface;

import java.util.List;

import org.json.JSONArray;

public interface ETLTool<T> {
	String extractCSV(String filePath) 	    throws Exception;
	List<T> transForm(JSONArray jsonArray)   throws Exception;
}
