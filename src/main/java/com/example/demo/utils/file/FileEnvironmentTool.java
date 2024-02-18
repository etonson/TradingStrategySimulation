package com.example.demo.utils.file;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class FileEnvironmentTool {
	private String fileDirPath;

	public FileEnvironmentTool(String rootPath, String fileChildDirPath) {
		this.fileDirPath = String.format("%s/%s", rootPath, fileChildDirPath);
		File dirPath = new File(fileDirPath);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
	}

	public String uploadFile(MultipartFile file) throws IOException {
		try {
			String filePath = fileDirPath + File.separator + file.getOriginalFilename();
			File localFile = new File(fileDirPath + File.separator + file.getOriginalFilename());
			file.transferTo(localFile);
			return filePath;
		} catch (IOException e) {
			throw e;
		}
	}

	public boolean deleteFile(String filePath) throws IllegalStateException, IOException {
		File localFile = new File(filePath);
		boolean result = true;
		if (localFile.exists()) {
			result = localFile.delete();
		}
		return result;
	}

	public String getFileDirPath() {
		return fileDirPath;
	}

	public void setFileDirPath(String fileDirPath) {
		this.fileDirPath = fileDirPath;
	}

}