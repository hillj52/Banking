package com.ssa.tiy.bank;

import java.util.HashMap;

public class Checking extends Account{
	
	private HashMap <Integer,Double> checkLog;
	
	public double withdraw(double amount) {
		System.out.println("Failed, must provide a check number");
		return this.getBalance();
	}
	
	public double withdraw(int checkNumber, double amount) {
		checkLog.put(checkNumber,amount);
		super.withdraw(amount);
		return this.getBalance();
	}
	
	public Checking(int id, String description) {
		super(id,description);
		this.checkLog = new HashMap<Integer,Double>();
	}
	
	public Checking(String description) {
		this(idGenner++,description);
	}
	
	public Checking() {
		this (idGenner++,"");
	}
}
