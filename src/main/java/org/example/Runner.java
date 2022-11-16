package org.example;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

@Slf4j
public class Runner {
    public static void main(String[] args) {
        HumanDto humanDto = null;;

        try ( SessionFactory sessionFactory = HibernateConfig.createSessionFactory();
        Session session = sessionFactory.openSession();) {
            //Получение данных по уровню изоляции транзакций
            //session.doWork((connection -> System.out.println(connection.getTransactionIsolation())));
            try {
                //Сохраняем данные
                session.beginTransaction();
                Passport passport = new Passport("4000", "123456", PassportType.FOREIGN_PASSPORT);
                Human human = new Human("Yuri", passport);
                Auto auto = new Auto("bmw", human);
                session.save(human);

                //Эмулируем возникновение ошибки внутри транзакции
//                int i = 1;
//                if (i == 1) {
//                    throw new RuntimeException("Проблема");
//                }

                session.save(auto);
                session.getTransaction().commit();
                session.clear();

                //Читаем сохраненные данные
                session.beginTransaction();
                Human human1 = session.get(Human.class, 1);
                //Получение lazy данных
                Hibernate.initialize(human1.getCars());
                //Преобразование в humanDto
                humanDto = new HumanDto(human1.getId(), human1.getName(), human1.getPassport(), human1.getCars());
                log.info(human1.toString());
                session.getTransaction().commit();

            } catch (Exception e) {
                log.info(e.getMessage());
                session.getTransaction().rollback();
            }
        }
        //Работа с данными из бд вне сессии
        List<Auto> cars = humanDto.getCars();
        System.out.println(cars);
    }
}
