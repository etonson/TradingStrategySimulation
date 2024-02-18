package com.example.demo.entities.commodities;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class GoldH1 implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private DatePK datePK;
	
	private double highPrice;
	
	private double lowPrice;
	
	private double openPrice;
	
	private double closePrice;
	
	private long updatedTime;
	
	public DatePK getDatePK() {
		return datePK;
	}
	public void setDatePK(DatePK datePK) {
		this.datePK = datePK;
	}
	public double getHighPrice() {
		return highPrice;
	}
	public void setHighPrice(double highPrice) {
		this.highPrice = highPrice;
	}
	public double getLowPrice() {
		return lowPrice;
	}
	public void setLowPrice(double lowPrice) {
		this.lowPrice = lowPrice;
	}
	public double getOpenPrice() {
		return openPrice;
	}
	public void setOpenPrice(double openPrice) {
		this.openPrice = openPrice;
	}
	public double getClosePrice() {
		return closePrice;
	}
	public void setClosePrice(double closePrice) {
		this.closePrice = closePrice;
	}
	public long getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(long updatedTime) {
		this.updatedTime = updatedTime;
	}
	@Override
	public String toString() {
		return "GoldH1 [datePK=" + datePK + ", highPrice=" + highPrice + ", lowPrice=" + lowPrice + ", openPrice="
				+ openPrice + ", closePrice=" + closePrice + ", updatedTime=" + updatedTime + "]";
	}
	
}
