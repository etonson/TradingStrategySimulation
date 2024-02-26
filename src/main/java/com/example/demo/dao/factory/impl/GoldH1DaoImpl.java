package com.example.demo.dao.factory.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.BasicEntityManager;
import com.example.demo.dao.factory.ICommodities;
import com.example.demo.entities.commodities.GoldH1;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Service
public class GoldH1DaoImpl extends BasicEntityManager implements ICommodities<GoldH1> {

	@Override
	public void save(GoldH1 goldH1) throws Exception {
		try {
			entityManager.persist(goldH1);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public String getMaxUpdateTime() throws Exception {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<String> sqlQueue = cb.createQuery(String.class);
		Root<GoldH1> root = sqlQueue.from(GoldH1.class);
		sqlQueue.multiselect(cb.max(root.get("updatedTime")));
		try {
			return entityManager.createQuery(sqlQueue).getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean batchSave(List<GoldH1> list) throws Exception {
		try {
			for (GoldH1 obj : list) {
				entityManager.persist(obj);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
