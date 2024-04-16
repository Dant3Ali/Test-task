package ru.test.Task.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.test.Task.dto.UserDto;
import ru.test.Task.payload.requests.MoneyTransferRequest;
import ru.test.Task.services.CentralBankServiceImpl;
import ru.test.Task.services.UserServiceImpl;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class MainController {

    @Autowired
    private final CentralBankServiceImpl centralBank;

    @Autowired
    private final UserServiceImpl userService;

    @PostMapping("/money")
    public String sendMoney(@RequestBody MoneyTransferRequest request, @AuthenticationPrincipal UserDetails userDetails) {
        String toBankAccount = request.getTo();
        Integer amount = request.getAmount();
        System.out.println(userDetails.getUsername());
        String fromBankAccount = userService.findByUserName(userDetails.getUsername()).getBankAccount().getBankAccountNumber();

        centralBank.transfer(amount, fromBankAccount, toBankAccount);

        return "DONE!!";
    }


    @GetMapping("/money")
    public ResponseEntity<String> getMoney(@AuthenticationPrincipal UserDetails userDetails) {
        try {
            UserDto user = userService.findByUserName(userDetails.getUsername());
            Long balance = user.getBankAccount().getBalance();

            return ResponseEntity.ok("Balance: " + balance);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error getting balance.");
        }
    }

}