package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getRoles() {
        return entityManager.createQuery("select role from Role role", Role.class).getResultList();
    }

    @Override
    public void setRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public Role getRole(int id) {
        return entityManager.createQuery("select role from Role role where role.id =:id", Role.class)
                        .setParameter("id", id)
                        .getSingleResult();
    }
}
