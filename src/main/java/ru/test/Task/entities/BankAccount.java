package ru.test.Task.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "bank_accounts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long balance;
    private LocalDate creationDate;
    private String bankAccountNumber;

    public BankAccount(String bankAccountNumber){
        this.bankAccountNumber = bankAccountNumber;
        this.balance = 0L;
        this.creationDate = LocalDate.now();
    }
}
