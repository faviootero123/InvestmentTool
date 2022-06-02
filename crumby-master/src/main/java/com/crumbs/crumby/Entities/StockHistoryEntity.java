package com.crumbs.crumby.Entities;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class StockHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    public Date addedOn;
    public BigDecimal price;
    public int stockId;
    public String symbol;
    public Calendar date;
    public BigDecimal open;
    public BigDecimal low;
    public BigDecimal high;
    public BigDecimal close;

    public BigDecimal adjClose;

    public Long volume;
    @ManyToOne
    @JoinColumn(name = "stonk_id", nullable = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    private Stonk stonk;

    public StockHistoryEntity() {
    }

    public StockHistoryEntity(int id, Date addedOn, BigDecimal price, int stockId, String symbol, Calendar date,
            BigDecimal open, BigDecimal low, BigDecimal high, BigDecimal close, BigDecimal adjClose, Long volume,
            Stonk stonk) {
        this.id = id;
        this.addedOn = addedOn;
        this.price = price;
        this.stockId = stockId;
        this.symbol = symbol;
        this.date = date;
        this.open = open;
        this.low = low;
        this.high = high;
        this.close = close;
        this.adjClose = adjClose;
        this.volume = volume;
        this.stonk = stonk;
    }

    public StockHistoryEntity(BigDecimal open, String symbol, Calendar convertor, BigDecimal high, BigDecimal low,
            BigDecimal close, BigDecimal adjClose, long volume) {
        this.open = open;
        this.addedOn = convertor.getTime();
        this.date = convertor;
        this.low = low;
        this.high = high;
        this.close = close;
        this.symbol = symbol;
        this.adjClose = adjClose;
        this.volume = volume;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(Date addedOn) {
        this.addedOn = addedOn;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
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

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public BigDecimal getAdjClose() {
        return adjClose;
    }

    public void setAdjClose(BigDecimal adjClose) {
        this.adjClose = adjClose;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public Stonk getStonk() {
        return stonk;
    }

    public void setStonk(Stonk stonk) {
        this.stonk = stonk;
    }

}
