package ru.test.Task.entities.transactoins;

import ru.test.Task.entities.BankAccount;

public record WithdrawTransaction(Integer amount, BankAccount account) implements BankTransaction {}
