package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.configuration.HibernateConfig;
import org.example.dao.CommentDao;
import org.example.dao.PostDao;
import org.example.dao.UserDao;
import org.example.utils.Statistics;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Slf4j
public class Runner {
    public static void main(String[] args) {

        UserDao userDao = new UserDao();
        PostDao postDao = new PostDao();
        CommentDao commentDao = new CommentDao();
        Statistics statistics = new Statistics();


        try (SessionFactory sessionFactory = HibernateConfig.createSessionFactory();
             Session session = sessionFactory.openSession();) {



            /*for (int i =0; i < 10; i++) {
                userDao.createUser(session);
            }

            for (int i = 0; i < 30; i++) {
                int userId = SqlUtil.getRandomIdUser(session);
                postDao.createNewPost(session, userDao.getUserById(session, userId));
            }

            for (int i = 0; i < 50; i++) {
                int userId = SqlUtil.getRandomIdUser(session);
                int postId = SqlUtil.getRandomIdPost(session);
                commentDao.createNewComment(session, userDao.getUserById(session, userId), postDao.getPostById(session, postId));
            }*/


            statistics.printInfo(session, userDao, postDao, commentDao);
            statistics.printUserInfo(session, "Annaniev Artem");
        }
    }
}
