package ru.test.Task.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.test.Task.dao.BankAccountDao;
import ru.test.Task.dto.BankAccountDto;
import ru.test.Task.mappers.BankAccountMapper;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountDao bankAccountDao;


    @Override
    @Transactional
    public List<BankAccountDto> getAllBankAccounts() {
        return bankAccountDao.findAll().stream().map(BankAccountMapper::bankAccountToBankAccountDto).toList();
    }

    @Override
    @Transactional
    public BankAccountDto createOrUpdateBankAccount(BankAccountDto user) {
        return BankAccountMapper.bankAccountToBankAccountDto(bankAccountDao.save(BankAccountMapper.bankAccountDtoToBankAccount(user)));
    }

    @Override
    @Transactional
    public BankAccountDto getBankAccountById(Long id) {
        return BankAccountMapper.bankAccountToBankAccountDto(bankAccountDao.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public BankAccountDto deleteBankAccount(Long id) {
        return bankAccountDao.findById(id).map(BankAccountMapper::bankAccountToBankAccountDto).orElse(null);
    }

    @Override
    @Transactional
    public BankAccountDto findByBankAccountNumber(String bankAccountNumber) {
        return BankAccountMapper.bankAccountToBankAccountDto(bankAccountDao.findByBankAccountNumber(bankAccountNumber));
    }
}
