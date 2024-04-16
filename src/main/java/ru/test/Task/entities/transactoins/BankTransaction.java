package ru.test.Task.entities.transactoins;

public sealed interface BankTransaction permits TransferTransaction, WithdrawTransaction, TopupTransaction {}
