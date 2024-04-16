package ru.test.Task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.test.Task.payload.requests.BankAccountRequest;
import ru.test.Task.services.UserService;

import java.util.NoSuchElementException;

@RestController
public class BankAccountController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/{userId}/bank-account")
    public ResponseEntity<String> addBankAccountToUser(@PathVariable("userId") Long userId, @RequestBody BankAccountRequest bankAccountRequest) {
        try {
            userService.addBankAccountToUser(userId, bankAccountRequest.getBankAccountNumber());
            return ResponseEntity.ok("Bank account added successfully.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding bank account.");
        }
    }
}