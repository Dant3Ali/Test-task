package ru.test.Task.services;

import ru.test.Task.entities.transactoins.TopupTransaction;
import ru.test.Task.entities.transactoins.TransferTransaction;
import ru.test.Task.entities.transactoins.WithdrawTransaction;

public interface CentralBankService {

    TopupTransaction topup(Integer amount, String bankAccountNumber);
    WithdrawTransaction withdraw(Integer amount, String bankAccountNumber);
    TransferTransaction transfer(Integer amount, String fromBankAccountNumber, String toBankAccountNumber);
}
