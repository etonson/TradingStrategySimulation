package com.example.demo.dao;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class BasicEntityManager {

	@PersistenceContext
	protected EntityManager entityManager;
}
