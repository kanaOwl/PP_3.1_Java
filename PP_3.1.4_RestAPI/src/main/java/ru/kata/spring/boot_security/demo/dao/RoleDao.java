package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;
import java.util.List;
import java.util.Set;

public interface RoleDao {

    List<Role> getRoles();

    void setRole(Role role);

    Role getRole(int id);
}
