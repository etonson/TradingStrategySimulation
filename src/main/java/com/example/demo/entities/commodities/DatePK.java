package com.example.demo.entities.commodities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class DatePK implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column(length = 8)
	private String date;
	@Column(length = 8)
	private String time;

	public DatePK() {
	}

	public DatePK(String date, String time) {
		super();
		this.date = date;
		this.time = time;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, time);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DatePK other = (DatePK) obj;
		return Objects.equals(date, other.date) && Objects.equals(time, other.time);
	}

	@Override
	public String toString() {
		return "DatePK [date=" + date + ", time=" + time + "]";
	}
}
