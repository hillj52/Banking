package com.ssa.tiy.bank;

public class mainline {
	
	public static void main(String[] args){
		Account a = new Account();
		a.deposit(100);
		System.out.println(a.getBalance());
		a.withdraw(100);
		System.out.println(a.getBalance());
	}

}
