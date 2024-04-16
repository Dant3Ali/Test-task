package ru.test.Task.entities.transactoins;

import ru.test.Task.entities.BankAccount;

public record TransferTransaction(Integer amount, BankAccount fromAccount, BankAccount toAccount) implements BankTransaction {}
