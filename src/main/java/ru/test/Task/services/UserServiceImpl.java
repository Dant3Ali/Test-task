package ru.test.Task.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.test.Task.dao.UserDao;
import ru.test.Task.dto.UserDto;
import ru.test.Task.entities.BankAccount;
import ru.test.Task.entities.User;
import ru.test.Task.mappers.UserMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserDao userDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = UserMapper.toUser(findByUserName(username));
        return UserMapper.toUserDto(user);
    }

    @Override
    @Transactional
    public List<UserDto> getAllUsers() {
        return userDao.findAll().stream().map(UserMapper::toUserDto).toList();
    }

    @Override
    @Transactional
    public UserDto createOrUpdateUser(UserDto entity) {
        return UserMapper.toUserDto(userDao.save(UserMapper.toUser(entity)));
    }

    @Override
    @Transactional
    public UserDto getUserById(Long id) {
        return UserMapper.toUserDto(userDao.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userDao.deleteById(id);
    }

    @Override
    @Transactional
    public UserDto findByUserName(String login) {
        if (userDao.findByLogin(login) == null){
            return null;
        }

        return UserMapper.toUserDto(userDao.findByLogin(login));
    }

    @Override
    @Transactional
    public void addBankAccountToUser(Long id, String bankAccountNumber) {
        if (userDao.findById(id).isEmpty()){
            System.out.println("There is no such user");
        }

        User user = userDao.findById(id).get();
        user.setBankAccount(new BankAccount(bankAccountNumber));
        userDao.save(user);
    }
}
