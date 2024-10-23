package com.example.bankapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bankapp.entity.Account;
import com.example.bankapp.repo.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	AccountRepository repo;
	
	@Override
	public Account createAccount(Account account) {
		Account account_saved = repo.save(account);
		return account_saved;
	}

	@Override
	public Account getAccountDetailsByAccountNumber(Long accountNumber) {
		
		Optional<Account> account = repo.findById(accountNumber);
		if (account.isEmpty()) {
			throw new RuntimeException("Account is not present");
		}
		Account account_found = account.get();
		return account_found;
	}

	@Override
	public List<Account> getAllAccountDetails() {
		
		List<Account> listOfAccounts = repo.findAll();
		return listOfAccounts;
	}

	@Override
	public Account depositAmount(Long accountnumber, Double amount) {
		
		Optional<Account> account = repo.findById(accountnumber);
		if (account.isEmpty())
		{
			throw new RuntimeException("Account is not present");
		}
		Account account_present = account.get();
		Double totalBalance = account_present.getAccount_balance()+amount;
		account_present.setAccount_balance(totalBalance);
		repo.save(account_present);
		
		return account_present;
	}

	@Override
	public Account withdrawAmount(Long accountNumber, Double amount) {
		
		Optional<Account> account = repo.findById(accountNumber);
		if (account.isEmpty())
		{
			throw new RuntimeException("Account is not present");
		}
		Account account_present = account.get();
		
		Double accountBalance = account_present.getAccount_balance()-amount;
		account_present.setAccount_balance(accountBalance);
		repo.save(account_present);
		return account_present;
	}

	@Override
	public void closeAccouunt(Long accountNumber) {
		
		getAccountDetailsByAccountNumber(accountNumber);
		repo.deleteById(accountNumber);
		
	}
}
