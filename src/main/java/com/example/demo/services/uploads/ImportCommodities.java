package com.example.demo.services.uploads;

import java.io.IOException;

import org.apache.logging.log4j.util.Strings;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.utils.file.FileEnvironmentTool;

@RestController
@RequestMapping(path="base")
public class ImportCommodities {
	@Value("${file.uploadFolder}")
	private String rootPath;

	@PostMapping(value = "/uploadCommodities")
	public String uploadCommodities(@RequestParam(value = "file", required = true) MultipartFile file) {
		file.getOriginalFilename();
		FileEnvironmentTool fileEnvironmentTool = new FileEnvironmentTool(rootPath, "/commodities");
		JSONObject result = null;
		try {
			result = new JSONObject();
			String filePath = fileEnvironmentTool.uploadFile(file);
			
			if(!Strings.isBlank(filePath)) {
				result.put("SUCCESS", true);
			}else {
				result.put("SUCCESS", false);
			}
			return result.toString();
		} catch (IOException e) {
			result = new JSONObject();
			result.put("SUCCESS", false);
			e.printStackTrace();
			return result.toString();
		}
	}
}
