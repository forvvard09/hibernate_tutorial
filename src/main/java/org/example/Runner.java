package org.example;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

@Slf4j
public class Runner {
    public static void main(String[] args) {
        //log.debug("hello");
        try ( SessionFactory sessionFactory = HibernateConfig.createSessionFactory();
        Session session = sessionFactory.openSession();) {
            session.beginTransaction();
            Human human = new Human("Yuri");
            Auto auto = new Auto("bmw", human);
            session.save(human);
            session.save(auto);
            session.getTransaction().commit();
            session.clear();
//
            session.beginTransaction();
            Human human1 = session.get(Human.class, 1);
            List<Auto> cars = human1.getCars();
            log.info(cars.toString());
            session.getTransaction().commit();


        }
    }
}
