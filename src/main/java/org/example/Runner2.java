package org.example;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

@Slf4j
public class Runner2 {

    /**
     * Пример работы с разными полями в одной и той же сущности
     */
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateConfig.createSessionFactory();
             Session session = sessionFactory.openSession();) {
            inizializeDb(session);

            HumanDao humanDao = new HumanDao();
            String humanName = humanDao.getHumanName(session, 1);
            log.info("_________________________");
            log.info(humanName);
            log.info("_________________________");
            List<Auto> humanCars = humanDao.getHumanCars(session, 1);
            log.info(humanCars.toString());
            log.info("_________________________");
        }
    }

    public static void inizializeDb(Session session) {
        session.beginTransaction();
        Passport passport = new Passport("4000", "123456", PassportType.FOREIGN_PASSPORT);
        Human human = new Human("Yuri", passport);
        Auto auto = new Auto("bmw", human);
        session.save(human);
        session.save(auto);
        session.getTransaction().commit();
        session.clear();
    }
}
