package ru.test.Task.services;

import org.springframework.stereotype.Service;
import ru.test.Task.dto.UserDto;
import ru.test.Task.entities.User;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
    UserDto createOrUpdateUser(UserDto user);
    UserDto getUserById(Long id);
    void deleteUser(Long id);
    UserDto findByUserName(String login);
    void addBankAccountToUser(Long id, String bankAccountNumber);
}
