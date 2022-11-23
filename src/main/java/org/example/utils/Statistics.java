package org.example.utils;

import org.example.dao.CommentDao;
import org.example.dao.PostDao;
import org.example.dao.UserDao;
import org.example.model.Comment;
import org.example.model.Post;
import org.example.model.User;
import org.hibernate.Session;

import java.util.List;

public class Statistics {

    public void printInfo(Session session, UserDao userDao, PostDao postDao, CommentDao commentDao) {
        int countUsers = userDao.getCountUsers(session).intValue();
        int countPosts = postDao.getCountPosts(session).intValue();
        int countComments = commentDao.getCountComments(session).intValue();

        System.out.println("=====All statistics=====");
        System.out.println("Count users: " + countUsers);
        System.out.println("Count posts: " + countPosts);
        System.out.println("Count comments: " + countComments);
        System.out.println("----------------------------------");


    }

    public void printUserInfo(Session session, String name) {
        UserDao userDao = new UserDao();
        System.out.println("=====User info");
        User user = userDao.getUserByName(session, name);
        if (user == null) {
            System.out.println("Пользователь с таким именем не найден.");
        } else {
            System.out.println("Пользователь найден.");
            System.out.println(getFirstPost(session, user.getId()));
            System.out.println(getCountCommentsForUser(session, user.getId()));
        }

        System.out.println("----------------------------------");

    }

    private String getFirstPost(Session session, int userId) {
        PostDao postDao = new PostDao();

        String result = "У пользователя не найдено постов.";

        List<Post> posts = postDao.getListPosts(session);
        for (Post item : posts) {
            if (item.getUser().getId() == userId) {
                result = String.format("Дата первого поста: %s%sТекст первого поста: %s", item.getCreated_at(), System.lineSeparator(), item.getText());
            }
        }
        return result;
    }

    private String getCountCommentsForUser(Session session, int userId) {
        CommentDao commentDao = new CommentDao();
        int count = 0;
        List<Comment> comments = commentDao.getListcomments(session);
        if (comments.size() > 0) {
            for (Comment item : comments) {
                if (item.getUser().getId() == userId) {
                    count++;
                }
            }

        }
        return String.format("%s: %s", "Колличество комментариев пользователя", count);

    }


}
