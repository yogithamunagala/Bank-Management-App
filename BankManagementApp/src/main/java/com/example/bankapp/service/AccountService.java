package com.example.bankapp.service;

import java.util.List;

import com.example.bankapp.entity.Account;

public interface AccountService {

	public Account createAccount(Account account);
	
	public Account getAccountDetailsByAccountNumber(Long accountNumber);
	
	public List<Account> getAllAccountDetails();
	
	public Account depositAmount(Long accountnumber, Double money);
	
	public Account withdrawAmount(Long accountNumber, Double amount);
	
	public void closeAccouunt(Long accountNumber);
	
	
}
