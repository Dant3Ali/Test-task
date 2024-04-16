package ru.test.Task.mappers;

import ru.test.Task.dto.BankAccountDto;
import ru.test.Task.entities.BankAccount;

public class BankAccountMapper {

    public static BankAccountDto bankAccountToBankAccountDto(BankAccount bankAccount) {
        return new BankAccountDto(
            bankAccount.getId(),
            bankAccount.getBalance(),
            bankAccount.getCreationDate(),
            bankAccount.getBankAccountNumber()
        );
    }

    public static BankAccount bankAccountDtoToBankAccount(BankAccountDto bankAccountDto) {
        return new BankAccount(
            bankAccountDto.getId(),
            bankAccountDto.getBalance(),
            bankAccountDto.getCreationDate(),
            bankAccountDto.getBankAccountNumber()
        );
    }
}
