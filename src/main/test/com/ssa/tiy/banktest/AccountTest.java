/**
 * 
 */
package com.ssa.tiy.banktest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ssa.tiy.bank.Account;

public class AccountTest {

	private Account account;
	
	@Before
	public void setUp() throws Exception {
		account = new Account();
	}

	/**
	 * Test method for {@link com.ssa.tiy.bank.Account#Account(int, java.lang.String)}.
	 */
	@Test
	public void testAccountIntString() {
		account = new Account(150,"Description");
		
		assertEquals("Account ID set by constructor",150,account.getId(),0);
		assertEquals("Description set by constructor","Description",account.getDescription());
		assertEquals("Balance set by default",0,account.getBalance(),0);
	}

	/**
	 * Test method for {@link com.ssa.tiy.bank.Account#Account(java.lang.String)}.
	 */
	@Test
	public void testAccountString() {
		account = new Account("Description");
		
		assertEquals("Description set by constructor","Description",account.getDescription());
		assertEquals("Balance set by default",0,account.getBalance(),0);
		if (account.getId() <= 0) {
			fail("Id not properly set by default");
		}
		
	}

	/**
	 * Test method for {@link com.ssa.tiy.bank.Account#Account()}.
	 */
	@Test
	public void testAccount() {
		account = new Account();
		
		assertEquals("Description set by default","",account.getDescription());
		assertEquals("Balance set by default",0,account.getBalance(),0);
		if (account.getId() <= 0) {
			fail("Id not properly set by default");
		}
	}

	/**
	 * Test method for {@link com.ssa.tiy.bank.Account#getId()}.
	 */
	@Test
	public void testGetId() {
		if (account.getId() <= 0) {
			fail("Id not properly set by default");
		}
	}

	/**
	 * Test method for {@link com.ssa.tiy.bank.Account#getBalance()}.
	 */
	@Test
	public void testGetBalance() {
		double testDepositAmount = 100;
		assertEquals("Deposit",testDepositAmount,account.deposit(testDepositAmount),0);
		assertEquals("getBalance",testDepositAmount,account.getBalance(),0);
	}

	/**
	 * Test method for {@link com.ssa.tiy.bank.Account#getBalanceString()}.
	 */
	@Test
	public void testGetBalanceString() {
		double testDepositAmount = 100;
		account.deposit(testDepositAmount);
		assertEquals("Balance in formatted String","100.00",account.getBalanceString());
	}

	/**
	 * Test method for {@link com.ssa.tiy.bank.Account#getDescription()}.
	 */
	@Test
	public void testGetDescription() {
		String testDescription = "description";
		account.setDescription(testDescription);
		assertEquals("getDescription",testDescription,account.getDescription());
	}

	/**
	 * Test method for {@link com.ssa.tiy.bank.Account#setDescription(java.lang.String)}.
	 */
	@Test
	public void testSetDescription() {
		String testDescription = "description";
		account.setDescription(testDescription);
		assertEquals("getDescription",testDescription,account.getDescription());
	}

	/**
	 * Test method for {@link com.ssa.tiy.bank.Account#deposit(double)}.
	 */
	@Test
	public void testDeposit() {
		
		//Ensure negative values cannot be deposited
		double badDeposit = -1000;
		assertEquals("Bad Deposit",0,account.deposit(badDeposit),0);
		
		double testDepositAmount = 100;
		assertEquals("Deposit",testDepositAmount,account.deposit(testDepositAmount),0);
		assertEquals("Balance",testDepositAmount,account.getBalance(),0);
	}

	/**
	 * Test method for {@link com.ssa.tiy.bank.Account#withdraw(double)}.
	 */
	@Test
	public void testWithdraw() {
		//Ensure negative values cannot be withdrawn
		double badWithdraw = -1000;
		assertEquals("Bad Withdraw",0,account.withdraw(badWithdraw),0);
		
		//Ensure amounts greater than the balance cannot be withdrawn
		double badWithdraw2 = 1000;
		double testDepositAmount = 100;
		account.deposit(testDepositAmount);
		account.withdraw(badWithdraw2);
		assertEquals("Overdraw",testDepositAmount,account.getBalance(),0);
		
		//Ensure proper withdraw functions
		double testWithdrawAmount = 50;
		assertEquals("Withdraw",testDepositAmount-testWithdrawAmount,account.withdraw(testWithdrawAmount),0);
		assertEquals("Balance",testDepositAmount-testWithdrawAmount,account.getBalance(),0);
		
		//Ensure account can be withdrawn to zero
		account.withdraw(account.getBalance());
		assertEquals("Withdraw full balance",0,account.getBalance(),0);
	}

	/**
	 * Test method for {@link com.ssa.tiy.bank.Account#transferTo(com.ssa.tiy.bank.Account, double)}.
	 */
	@Test
	public void testTransferTo() {
		double testDeposit = 100;
		double testXfer = 100;
		Account otherAccount = new Account();
		
		account.deposit(testDeposit);
		account.transferTo(otherAccount, testXfer);
		
		assertEquals("Source balance after Xfer",0,account.getBalance(),0);
		assertEquals("Destination balance after Xfer",100,otherAccount.getBalance(),0);
	}

	/**
	 * Test method for {@link com.ssa.tiy.bank.Account#transferFrom(com.ssa.tiy.bank.Account, double)}.
	 */
	@Test
	public void testTransferFrom() {
		double testDeposit = 100;
		double testXfer = 100;
		Account otherAccount = new Account();
		
		account.deposit(testDeposit);
		otherAccount.transferFrom(account, testXfer);
		
		assertEquals("Source balance after Xfer",0,account.getBalance(),0);
		assertEquals("Destination balance after Xfer",100,otherAccount.getBalance(),0);
	}

	/**
	 * Test method for {@link com.ssa.tiy.bank.Account#print()}.
	 */
	@Test
	public void testPrint() {
		String expected = "Account " + account.getId() + 
				" balance is $" + account.getBalanceString();
		assertEquals("Print String",expected,account.print());
	}


}
