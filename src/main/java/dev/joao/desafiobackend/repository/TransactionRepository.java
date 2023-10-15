package dev.joao.desafiobackend.repository;

import dev.joao.desafiobackend.domain.transactions.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
