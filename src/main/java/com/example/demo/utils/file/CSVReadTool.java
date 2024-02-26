package com.example.demo.utils.file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.demo.enums.ETLError;
import com.example.demo.exception.ReferenceException;

public class CSVReadTool {
	private String filePath;

	public CSVReadTool(String filePath) {
		this.filePath = filePath;
	}

	public String readCSV() {
		JSONArray jsonArray = null;
		JSONObject result = null;
		try (InputStreamReader isr = new InputStreamReader(new FileInputStream(filePath));
				BufferedReader reader = new BufferedReader(isr);) {
			String line = null;
			result = new JSONObject();
			jsonArray = new JSONArray();
			while ((line = reader.readLine()) != null) {
				JSONObject jsonObj = createJSONObject(line);
				jsonArray.put(jsonObj);
				jsonObj = null;
			}
			result.put("SUCCESS", true);
			result.put("rm", jsonArray);
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

	public String readCSVFilteGreatUpdatedTime(String filtedUpdatedTime) throws ReferenceException {
		JSONArray jsonArray = null;
		JSONObject result= new JSONObject();
		try (InputStreamReader isr = new InputStreamReader(new FileInputStream(filePath));
				BufferedReader reader = new BufferedReader(isr);) {
			String line = null;
			jsonArray = new JSONArray();
			while ((line = reader.readLine()) != null) {
				String item[] = line.split(",");
				String date = item[0].trim().replace(".", "");
				String time = item[1].trim().replace(":", "");
				String updatedTime = String.format("%s%s", date, time);
				if(Long.valueOf(updatedTime)>Long.valueOf(filtedUpdatedTime)) {
					JSONObject jsonObj = createJSONObject(line);
					jsonArray.put(jsonObj);
					jsonObj = null;
				}
			}
			result.put("SUCCESS", true);
			result.put("rm", jsonArray);
			return result.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new ReferenceException(ETLError.NOT_FIND_FILE);
		} catch (Exception e) {
			result.put("SUCCESS", false);
			result.put("rm", e.toString());
			e.printStackTrace();
			return result.toString();
		}
	}

	JSONObject createJSONObject(String line) {
		JSONObject jsonObj = new JSONObject();
		String item[] = line.split(",");
		String date = item[0].trim().replace(".", "");
		jsonObj.put("date", date);
		String time = item[1].trim().replace(":", "");
		jsonObj.put("time", item[1].trim());
		jsonObj.put("openPrice", item[2].trim());
		jsonObj.put("highPrice", item[3].trim());
		jsonObj.put("lowPrice", item[4].trim());
		jsonObj.put("closePrice", item[5].trim());
		jsonObj.put("volumn", item[6].trim());
		String updatedTime = String.format("%s%s", date, time);
		jsonObj.put("updatedTime", updatedTime);
		return jsonObj;
	}
}