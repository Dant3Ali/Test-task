package ru.test.Task.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.test.Task.entities.BankAccount;

import java.util.Optional;

@Repository
public interface BankAccountDao extends JpaRepository<BankAccount, Long> {
    Optional<BankAccount> findById(Long id);
    BankAccount findByBankAccountNumber(String bankAccountNumber);
}
