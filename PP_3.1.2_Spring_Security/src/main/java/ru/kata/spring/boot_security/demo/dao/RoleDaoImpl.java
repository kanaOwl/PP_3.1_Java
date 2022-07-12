package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getRoleList() {
        return entityManager.createQuery("select role from Role role", Role.class).getResultList();
    }

    @Override
    public Set<Role> getRoleSet(int roleId) {
        Set<Role> roleResult = new HashSet<>();
            roleResult.add(entityManager
                    .createQuery("select role from Role role where role.id = :id", Role.class)
                    .setParameter("id", roleId)
                    .getResultList()
                    .stream()
                    .filter(role -> role.getId() == roleId)
                    .findAny()
                    .orElse(null));
        return roleResult;
    }

    @Override
    public void saveRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public Role getRole(String name) {
        return entityManager
                .createQuery("select role from Role role where role.name = :name", Role.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
