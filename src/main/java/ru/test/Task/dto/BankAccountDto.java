package ru.test.Task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountDto {
    private Long id;
    private Long balance;
    private LocalDate creationDate;
    private String bankAccountNumber;
}
