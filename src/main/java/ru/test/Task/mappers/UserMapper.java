package ru.test.Task.mappers;

import ru.test.Task.dto.UserDto;
import ru.test.Task.entities.ERole;
import ru.test.Task.entities.Role;
import ru.test.Task.entities.User;

import java.util.Set;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword());
        userDto.setAuthorities(user.getRoles());
        userDto.setBankAccount(user.getBankAccount());
        return userDto;
    }

    public static User toUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        Set<Role> roles = userDto.getAuthorities().stream()
                .map(authority -> {
                    Role role = new Role();
                    role.setName(ERole.valueOf(authority.getAuthority()));
                    return role;
                })
                .collect(Collectors.toSet());
        user.setRoles(roles);
        user.setBankAccount(userDto.getBankAccount());
        return user;
    }
}
