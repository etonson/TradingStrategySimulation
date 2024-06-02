package com.example.demo.etls.services;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.factory.impl.GoldH1DaoImpl;
import com.example.demo.entities.commodities.DatePK;
import com.example.demo.entities.commodities.GoldH1;
import com.example.demo.etls.iface.ETLTool;
import com.example.demo.exception.ReferenceException;
import com.example.demo.utils.file.CSVReadTool;

//@Service
@RestController
@RequestMapping(path="etls")
public class GoldH1ETLImpl implements ETLTool<GoldH1> {
	@Autowired
	private GoldH1DaoImpl goldH1DaoImpl;

	@Value("${file.uploadFolder}")
	private String rootPath;
	private String maxUpdateTime = null;

	@PostMapping(value = "/goldH1ETLImpl")
	public String controllers() {
		JSONObject result=null;
		try {
			result = new JSONObject();
			String filePath = rootPath + "\\commodities\\XAUUSD60.csv";
			maxUpdateTime = goldH1DaoImpl.getMaxUpdateTime();
			JSONObject jsonObj = new JSONObject(extractCSV(filePath));
			JSONArray jsonArray = null;
			if(jsonObj.optBoolean("SUCCESS")) {
				jsonArray = jsonObj.optJSONArray("rm");
				jsonObj = null;
				List<GoldH1> loadList = transForm(jsonArray);
				jsonArray = null;
				boolean loadResult =goldH1DaoImpl.batchSave(loadList);
				loadList = null;
				result.put("SUCCESS", loadResult);
			}else {
				result.put("SUCCESS", false);
				throw new Exception();
			}
			
			return result.toString();
		}catch(ReferenceException re) {
			return re.toJson().toString();
		}
		catch (Exception e) {
			e.printStackTrace();
			result.put("SUCCESS", false);
			result.put("rc", e.getCause());
			result.put("rm", e.toString());
			return result.toString();
		}
	}

	@Override
	public String extractCSV(String filePath) throws Exception {
		CSVReadTool csvReadTool = new CSVReadTool(filePath);
		return csvReadTool.readCSVFilteGreatUpdatedTime(maxUpdateTime);
	}


	@Override
	public List<GoldH1> transForm(JSONArray jsonArray) throws Exception {
		List<GoldH1> result = new ArrayList<GoldH1>();
		for(int i=0;i<jsonArray.length()-1;i++) {
			JSONObject jsonObj = jsonArray.optJSONObject(i);
			DatePK pk = new DatePK();
			pk.setDate(jsonObj.optString("date"));
			pk.setTime(jsonObj.optString("time"));
			GoldH1 obj = new GoldH1();
			obj.setDatePK(pk);
			obj.setOpenPrice(jsonObj.optDouble("openPrice"));
			obj.setHighPrice(jsonObj.optDouble("highPrice"));
			obj.setLowPrice(jsonObj.optDouble("lowPrice"));
			obj.setClosePrice(jsonObj.optDouble("closePrice"));
			obj.setVolumn(jsonObj.optInt("volumn"));
			obj.setUpdatedTime(jsonObj.optString("updatedTime"));
			result.add(obj);
		}
		return result;
	}
}
