package com.crumbs.crumby.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)

public class StockHistory {
	private String symbol;
	private String date;
	private BigDecimal high;
	private BigDecimal low;
	private BigDecimal close;

	public StockHistory(String symbol, String date, BigDecimal high, BigDecimal low, BigDecimal close) {
		this.symbol = symbol;
		this.date = date;
		this.high = high;
		this.low = low;
		this.setClose(close);
	}

	public BigDecimal getClose() {
		return close;
	}

	public void setClose(BigDecimal close) {
		this.close = close;
	}

	public String getSymbol() {
		return symbol;
	}

	public BigDecimal getLow() {
		return low;
	}

	public void setLow(BigDecimal low) {
		this.low = low;
	}

	public BigDecimal getHigh() {
		return high;
	}

	public void setHigh(BigDecimal high) {
		this.high = high;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
}