package com.example.demo.entities.commodities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class CommonCommodity {
	@EmbeddedId
	private DatePK datePK;
	@Column(columnDefinition = " DECIMAL(20,10) ")
	private double highPrice;
	@Column(columnDefinition = " DECIMAL(20,10) ")
	private double lowPrice;
	@Column(columnDefinition = " DECIMAL(20,10) ")
	private double openPrice;
	@Column(columnDefinition = " DECIMAL(20,10) ")
	private double closePrice;

	private int volumn;
	@Column(length = 16)
	private String updatedTime;

	public DatePK getDatePK() {
		return datePK;
	}

	public void setDatePK(DatePK datePK) {
		this.datePK = datePK;
	}

	public Double getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(Double highPrice) {
		this.highPrice = highPrice;
	}

	public Double getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(Double lowPrice) {
		this.lowPrice = lowPrice;
	}

	public Double getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(Double openPrice) {
		this.openPrice = openPrice;
	}

	public Double getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(Double closePrice) {
		this.closePrice = closePrice;
	}

	public int getVolumn() {
		return volumn;
	}

	public void setVolumn(int volumn) {
		this.volumn = volumn;
	}

	public String getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}

	@Override
	public String toString() {
		return "CommonCommodity [datePK=" + datePK + ", highPrice=" + highPrice + ", lowPrice=" + lowPrice
				+ ", openPrice=" + openPrice + ", closePrice=" + closePrice + ", updatedTime=" + updatedTime + "]";
	}
}
