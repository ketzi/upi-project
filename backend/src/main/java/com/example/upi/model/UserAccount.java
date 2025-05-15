package com.example.upi.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 10)
    private String phoneNumber;

    private double balance = 0;
    private boolean upiEnabled = false;

    private int dailyTransferCount = 0;
    private double dailyTransferAmount = 0;
    private LocalDate lastTransferDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public boolean isUpiEnabled() {
		return upiEnabled;
	}
	public void setUpiEnabled(boolean upiEnabled) {
		this.upiEnabled = upiEnabled;
	}
	public int getDailyTransferCount() {
		return dailyTransferCount;
	}
	public void setDailyTransferCount(int dailyTransferCount) {
		this.dailyTransferCount = dailyTransferCount;
	}
	public double getDailyTransferAmount() {
		return dailyTransferAmount;
	}
	public void setDailyTransferAmount(double dailyTransferAmount) {
		this.dailyTransferAmount = dailyTransferAmount;
	}
	public LocalDate getLastTransferDate() {
		return lastTransferDate;
	}
	public void setLastTransferDate(LocalDate lastTransferDate) {
		this.lastTransferDate = lastTransferDate;
	}

    
}
