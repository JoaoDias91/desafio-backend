package dev.joao.desafiobackend.controllers;

import dev.joao.desafiobackend.domain.transactions.Transaction;
import dev.joao.desafiobackend.dtos.TransactionDTO;
import dev.joao.desafiobackend.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO newTransaction) throws Exception {
        Transaction transaction = service.createTransaction(newTransaction);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }
}
