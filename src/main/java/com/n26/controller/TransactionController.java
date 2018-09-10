package com.n26.controller;

import com.n26.controller.validator.TransactionValidator;
import com.n26.entity.Transaction;
import com.n26.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class TransactionController {
    private TransactionService transactionsService;

    @Autowired
    public TransactionController(TransactionService transactionsService) {
        this.transactionsService = transactionsService;
    }

    @PostMapping("/transactions")
    public ResponseEntity<Void> addTransaction(@RequestBody @Valid Transaction transaction) {
        transactionsService.addTransaction(transaction);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/transactions")
    public ResponseEntity<Void> deleteAllTransactions() {
        transactionsService.deleteAllTransactions();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @InitBinder("transaction")
    public void setupBinder(WebDataBinder binder) {
        binder.setValidator( new TransactionValidator());
    }
}
 