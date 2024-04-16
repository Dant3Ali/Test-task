package ru.test.Task.services;

import ru.test.Task.dto.BankAccountDto;

import java.util.List;

public interface BankAccountService {

    List<BankAccountDto> getAllBankAccounts();
    BankAccountDto createOrUpdateBankAccount(BankAccountDto user);
    BankAccountDto getBankAccountById(Long id);
    BankAccountDto deleteBankAccount(Long id);
    BankAccountDto findByBankAccountNumber(String bankAccountNumber);
}
