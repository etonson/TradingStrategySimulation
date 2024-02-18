package com.example.demo.utils.file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONObject;

public class CSVReadTool {
	private String filePath;

	public CSVReadTool(String filePath) {
		this.filePath = filePath;
	}

	public String readCSV() {
		JSONArray jsonArray = null;
		JSONObject result =null;
		try {
			InputStreamReader isr = new InputStreamReader(new FileInputStream(filePath));
			BufferedReader reader = new BufferedReader(isr);
			String line = null;
			result = new JSONObject();
			jsonArray = new JSONArray();
			while ((line = reader.readLine()) != null) {
				JSONObject jsonObj = new JSONObject();
				String item[] = line.split(",");
				jsonObj.put("date", item[0].trim());
				jsonObj.put("time", item[1].trim());
				jsonObj.put("openPrice", item[2].trim());
				jsonObj.put("highPrice", item[3].trim());
				jsonObj.put("lowPrice", item[4].trim());
				jsonObj.put("closePrice", item[5].trim());
				jsonObj.put("volumn", item[6].trim());
				jsonArray.put(jsonObj);
			}
			result.put("SUCCESS", true);
			result.put("rm", jsonArray);
			reader.close();
			return result.toString();
		} catch (FileNotFoundException e) {
			result = new JSONObject();
			result.put("SUCCESS", false);
			result.put("rm", e.toString());
			e.printStackTrace();
			return result.toString();
		} catch (Exception e) {
			result = new JSONObject();
			result.put("SUCCESS", false);
			result.put("rm", e.toString());
			e.printStackTrace();
			return result.toString();
		}
	}
}
