package ru.test.Task.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.test.Task.entities.BankAccount;
import ru.test.Task.entities.Role;

import java.util.Collection;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements UserDetails {

    private Long id;
    private String login;
    @JsonIgnore
    private String password;
    private Collection<Role> authorities;
    private BankAccount bankAccount;

    @Override
    public String getUsername() {
        return login;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Преобразовать коллекцию ролей в коллекцию GrantedAuthority
        return authorities.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().toString()))
                .collect(Collectors.toList());
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
