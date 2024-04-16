package ru.test.Task.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.test.Task.entities.ERole;
import ru.test.Task.entities.Role;

import java.util.Optional;

@Repository
public interface RoleDao extends JpaRepository<Role, Integer> {
    Optional<Role> getRoleByName(ERole name);
}
