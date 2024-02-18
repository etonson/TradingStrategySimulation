package com.example.demo.entities.commodities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class CommonCommodity{
	@EmbeddedId
	private DatePK datePK;
	@Column(columnDefinition = " DECIMAL(7,6) ")
	private Double  highPrice;
	@Column(columnDefinition = " DECIMAL(7,6) ")
	private Double  lowPrice;
	@Column(columnDefinition = " DECIMAL(7,6) ")
	private Double  openPrice;
	@Column(columnDefinition = " DECIMAL(7,6) ")
	private Double  closePrice;
	@Column(length = 16)
	private String updatedTime;

	public DatePK getDatePK() {
		return datePK;
	}

	public void setDatePK(DatePK datePK) {
		this.datePK = datePK;
	}


	@Override
	public String toString() {
		return "CommonCommodity [datePK=" + datePK + ", highPrice=" + highPrice + ", lowPrice=" + lowPrice
				+ ", openPrice=" + openPrice + ", closePrice=" + closePrice + ", updatedTime=" + updatedTime + "]";
	}
}
