package ru.test.Task.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.test.Task.entities.BankAccount;
import ru.test.Task.entities.User;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);
    User findByLogin(String login);
    Optional<BankAccount> findBankAccountByLogin(String login);
    boolean existsByLogin(String login);
}
