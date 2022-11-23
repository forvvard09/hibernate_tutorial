package org.example.dao;

import org.example.model.User;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.util.List;

import static org.example.utils.SqlUtil.getRandomName;
import static org.example.utils.SqlUtil.getRandomPsw;

public class UserDao {

    public List<User> getListUsers(Session session) {
        return session.createQuery("from User", User.class).list();
    }

    public void createUser(Session session) {
        session.beginTransaction();
        User user = new User(getRandomName(), getRandomPsw());
        session.saveOrUpdate(user);
        session.getTransaction().commit();
    }

    public User getUserById(Session session, int userId) {
        User user;
        session.beginTransaction();
        user = session.get(User.class, userId);
        session.getTransaction().commit();
        return user;
    }
    public User getUserByName(Session session, String name) {
        User user;
        user = session.createQuery("from User u where u.name = :name", User.class)
                .setParameter("name", name)
                .uniqueResult();
        return user;
    }

    public Long getCountUsers(Session session) {
        return (Long) session.createQuery("select count(1) from User ").getSingleResult();
    }
}
