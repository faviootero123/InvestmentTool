package com.crumbs.crumby.Entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int userId;
    public BigDecimal startingAmount;
    public Date createdOn;
    public Date updatedOn;
    @OneToMany(mappedBy = "user")
    public Set<Stonk> stonks;

    public User() {
    }

    public User(int userId, BigDecimal startingAmount, Date createdOn, Date updatedOn, Set<Stonk> stonks) {
        this.userId = userId;
        this.startingAmount = startingAmount;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.stonks = stonks;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BigDecimal getStartingAmount() {
        return startingAmount;
    }

    public void setStartingAmount(BigDecimal startingAmount) {
        this.startingAmount = startingAmount;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Set<Stonk> getStonks() {
        return stonks;
    }

    public void setStonks(Set<Stonk> stonks) {
        this.stonks = stonks;
    }
}
