package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> getAllRolesList() {
        return roleDao.getRoleList();
    }

    @Override
    public Set<Role> getRoleSet(int id) {
        return roleDao.getRoleSet(id);
    }

    @Override
    public void setRole(Role role) {
        roleDao.saveRole(role);
    }

    @Transactional(readOnly = true)
    @Override
    public Role getRole(String name) {
        return roleDao.getRole(name);
    }
}
