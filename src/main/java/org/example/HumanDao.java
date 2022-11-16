package org.example;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import java.util.List;

public class HumanDao {

    public String getHumanName(Session session, Integer humanId) {
        session.beginTransaction();
        String name = session.get(Human.class, humanId).getName();
        session.getTransaction().commit();
        session.clear();
        return name;
    }

    public List<Auto> getHumanCars(Session session, Integer humanId) {
        session.beginTransaction();
        Human human = session.get(Human.class, humanId);
        Hibernate.initialize(human.getCars());
        List<Auto> cars = human.getCars();
        session.getTransaction().commit();
        session.clear();
        return cars;
    }
}
