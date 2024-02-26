package com.example.demo.dao.factory;

import java.util.List;

public interface ICommodities<T> {
	public void save(T t) throws Exception;
	public boolean batchSave(List<T> list) throws Exception;
	public String getMaxUpdateTime() throws Exception;
}