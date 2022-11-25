package org.example.dao;

import org.example.dto.BigUserDTO;
import org.example.model.Passport;
import org.example.model.PassportType;
import org.example.model.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import static org.example.utils.SqlUtil.getRandomName;
import static org.example.utils.SqlUtil.getRandomPsw;

public class UserDao {

    public List<User> getListUsers(Session session) {
        return session.createQuery("from User", User.class).list();
    }

    public void createUser(Session session, String userName, String password) {
        session.beginTransaction();
        User user = new User(userName, password, new Passport("0000", "123456", PassportType.PASSPORT));
        session.saveOrUpdate(user);
        session.flush();
        session.getTransaction().commit();
    }

    public User getUserById(Session session, int userId) {
        User user;
        session.beginTransaction();
        user = session.get(User.class, userId);
        session.flush();
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

    public BigUserDTO getBigUserByName(Session session, String name) {
        BigUserDTO bigUserDTO = new BigUserDTO();
        User user = session.createQuery("from User u where u.name = :name", User.class)
                .setParameter("name", name)
                .uniqueResult();
        //user.getPosts().get(0);
        Hibernate .initialize(user.getPosts());
        bigUserDTO.setName(user.getName());
        bigUserDTO.setPosts(user.getPosts());
        return bigUserDTO;
    }

    public Long getCountUsers(Session session) {
        return (Long) session.createQuery("select count(1) from User ").getSingleResult();
    }
}
