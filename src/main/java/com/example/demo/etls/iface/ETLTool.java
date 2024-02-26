package com.example.demo.etls.iface;

import java.util.List;

import org.json.JSONArray;

public interface ETLTool<T> {
	public String extractCSV(String filePath) 	    throws Exception;
	public List<T> transForm(JSONArray jsonArray)   throws Exception;
}
