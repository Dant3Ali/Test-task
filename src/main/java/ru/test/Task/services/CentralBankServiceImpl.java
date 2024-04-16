package ru.test.Task.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.test.Task.dto.BankAccountDto;
import ru.test.Task.entities.transactoins.TopupTransaction;
import ru.test.Task.entities.transactoins.TransferTransaction;
import ru.test.Task.entities.transactoins.WithdrawTransaction;
import ru.test.Task.mappers.BankAccountMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class CentralBankServiceImpl implements CentralBankService {
    private final BankAccountServiceImpl bankAccountService;
    private static final Logger logger = LoggerFactory.getLogger(CentralBankServiceImpl.class);

    @Override
    public TopupTransaction topup(Integer amount, String bankAccountNumber) {
        logger.info("Performing top-up transaction...");
        BankAccountDto bankAccountDto = bankAccountService.findByBankAccountNumber(bankAccountNumber);
        bankAccountDto.setBalance(bankAccountDto.getBalance() + amount);
        logger.info("Top-up transaction completed successfully.");
        return new TopupTransaction(amount, BankAccountMapper.bankAccountDtoToBankAccount(bankAccountDto));
    }

    @Override
    public WithdrawTransaction withdraw(Integer amount, String bankAccountNumber) {
        logger.info("Performing withdrawal transaction...");
        BankAccountDto bankAccountDto = bankAccountService.findByBankAccountNumber(bankAccountNumber);
        bankAccountDto.setBalance(bankAccountDto.getBalance() - amount);
        logger.info("Withdrawal transaction completed successfully.");
        return new WithdrawTransaction(amount, BankAccountMapper.bankAccountDtoToBankAccount(bankAccountDto));
    }

    @Override
    public TransferTransaction transfer(Integer amount, String fromBankAccountNumber, String toBankAccountNumber) {
        logger.info("Performing transfer transaction...");
        BankAccountDto fromBankAccountDto = bankAccountService.findByBankAccountNumber(fromBankAccountNumber);
        BankAccountDto toBankAccountDto = bankAccountService.findByBankAccountNumber(toBankAccountNumber);
        fromBankAccountDto.setBalance(fromBankAccountDto.getBalance() - amount);
        toBankAccountDto.setBalance(toBankAccountDto.getBalance() + amount);
        logger.info("Transfer transaction completed successfully.");
        return new TransferTransaction(amount, BankAccountMapper.bankAccountDtoToBankAccount(fromBankAccountDto), BankAccountMapper.bankAccountDtoToBankAccount(toBankAccountDto));
    }
}
