package com.crumbs.crumby.Entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Stonk {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int stonkId;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    private User user;

    public String name;
    public BigDecimal price;
    public Date addedOn;
    public BigDecimal change;
    public String currency;
    public int percentage;
    public BigDecimal amount;
    public String stockName;
    @OneToMany(mappedBy = "stonk")

    public Set<StockHistoryEntity> stockHistories;

    public Stonk() {
    }

    public Stonk(int stonkId, User user, String stockName, String name, BigDecimal price, Date addedOn,
            BigDecimal change, String currency, int percentage, Set<StockHistoryEntity> stockHistories,
            BigDecimal amount) {
        this.stonkId = stonkId;
        this.user = user;
        this.stockName = stockName;
        this.name = name;
        this.price = price;
        this.addedOn = addedOn;
        this.change = change;
        this.currency = currency;
        this.percentage = percentage;
        this.stockHistories = stockHistories;
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getStonkId() {
        return stonkId;
    }

    public void setStonkId(int stonkId) {
        this.stonkId = stonkId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(Date addedOn) {
        this.addedOn = addedOn;
    }

    public BigDecimal getChange() {
        return change;
    }

    public void setChange(BigDecimal change) {
        this.change = change;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public Set<StockHistoryEntity> getStockHistories() {
        return stockHistories;
    }

    public void setStockHistories(Set<StockHistoryEntity> stockHistories) {
        this.stockHistories = stockHistories;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }
}