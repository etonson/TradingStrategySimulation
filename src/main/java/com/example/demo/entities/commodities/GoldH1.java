package com.example.demo.entities.commodities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "GOLD_H1")
public class GoldH1 extends CommonCommodity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "GoldH1 ["+ super.toString() + "]";
	}
}
