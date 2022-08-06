package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("from User", User.class)
                .getResultList();
    }

    @Override
    public void updateUser(User newUser) {
        entityManager.merge(newUser);
    }

    @Override
    public User getUser(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void deleteUser(User user) {
        entityManager.createQuery("delete from User where id =:userID")
                .setParameter("userID", user.getId())
                .executeUpdate();
    }

    @Override
    public void setUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User loadByUsername(String username) {
        return entityManager.createQuery(
                "select user from User user where user.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }
}
