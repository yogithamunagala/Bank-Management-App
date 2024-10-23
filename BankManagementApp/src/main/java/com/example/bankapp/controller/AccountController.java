package com.example.bankapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankapp.entity.Account;
import com.example.bankapp.service.AccountService;

@RestController
@RequestMapping("account")
public class AccountController {
	
	@Autowired
	AccountService service;
	
	//create the account
	@PostMapping("/create")
	public ResponseEntity<Account> createAccount(@RequestBody Account account)
	{
		Account createAccount = service.createAccount(account);
		return ResponseEntity.status(HttpStatus.CREATED).body(createAccount);
	}
	
	@GetMapping("/{accountNumber}")
	public Account getAccountNumber(@PathVariable Long accountNumber) 
	{
		Account account = service.getAccountDetailsByAccountNumber(accountNumber);
		return account;
	}
	
	@GetMapping("/getallaccounts")
	public List<Account> getAllAccountDetails()
	{
		List<Account> allAccountDetails = service.getAllAccountDetails();
		return allAccountDetails;
	}
	
	@PutMapping("/deposit/{accountNumber}/{amount}")
	public Account depositAccount(@PathVariable Long accountNumber,@PathVariable Double amount) 
	{
		Account account = service.depositAmount(accountNumber, amount);
		return account;
	}
	
	@PutMapping("/withdraw/{accountNumber}/{amount}")
	public Account withdrawAccount(@PathVariable Long accountNumber,@PathVariable Double amount) 
	{
		Account account = service.withdrawAmount(accountNumber, amount);
		return account;
	}
	
	@DeleteMapping("/delete/{accountNumber}")
	public ResponseEntity deleteAccount(@PathVariable Long accountNumber) 
	{
		service.closeAccouunt(accountNumber);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Account closed");
	}
}
