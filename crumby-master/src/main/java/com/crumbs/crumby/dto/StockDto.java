package com.crumbs.crumby.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class StockDto {
	private String name;
	private BigDecimal price;
	private BigDecimal change;
	private String currency;
	private BigDecimal bid;
	private BigDecimal priceHint;
	private String stockName;

	public StockDto(String name, BigDecimal price, BigDecimal change, String currency, BigDecimal bid,
			BigDecimal priceHint, String stockName) {
		this.name = name;
		this.price = price;
		this.change = change;
		this.setCurrency(currency);
		this.setBid(bid);
		this.setPriceHint(priceHint);
		this.setStockName(stockName);
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public BigDecimal getPriceHint() {
		return priceHint;
	}

	public void setPriceHint(BigDecimal priceHint) {
		this.priceHint = priceHint;
	}

	public BigDecimal getBid() {
		return bid;
	}

	public void setBid(BigDecimal bid) {
		this.bid = bid;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getChange() {
		return change;
	}

	public void setChange(BigDecimal change) {
		this.change = change;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setName(String name) {
		this.name = name;
	}
}