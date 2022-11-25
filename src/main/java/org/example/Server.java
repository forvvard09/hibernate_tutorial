package org.example;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.configuration.HibernateConfig;
import org.example.dao.CommentDao;
import org.example.dao.PostDao;
import org.example.dao.UserDao;
import org.example.dto.BigUserDTO;
import org.example.dto.CommentDto;
import org.example.dto.PostDto;
import org.example.dto.UserDto;
import org.example.model.Comment;
import org.example.model.Post;
import org.example.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;


@AllArgsConstructor
@Slf4j

public class Server {

    private final UserDao userDao;
    private final PostDao postDao;
    private final CommentDao commentDao;

    private final SessionFactory sessionFactory = HibernateConfig.createSessionFactory();


    public void createUser(UserDto userDto) {
        try (Session session = sessionFactory.openSession()) {
            userDao.createUser(session, userDto.getName(), userDto.getPassword());
        }
    }

    public List<User> getListUser() {
        try (Session session = sessionFactory.openSession()) {
            return userDao.getListUsers(session);
        }
    }

    public User getUserById(int userId) {
        try (Session session = sessionFactory.openSession()) {
            return userDao.getUserById(session, userId);
        }
    }


    public void createPost(PostDto postDto) {
        try (Session session = sessionFactory.openSession()) {
            User user = getUserById(postDto.getUserId());
            postDao.createNewPost(session, postDto.getText(), user);
        }

    }


    public List<Post> getListPost() {
        try (Session session = sessionFactory.openSession()) {
            return postDao.getListPosts(session);
        }
    }

    public List<Comment> getListComment() {
        try (Session session = sessionFactory.openSession()) {
            return commentDao.getListcomments(session);
        }
    }

    public void createComment(CommentDto commentDto) {
        try (Session session = sessionFactory.openSession()) {
            User user = getUserById(commentDto.getUserId());
            Post post = getPostById(commentDto.getPostId());
            commentDao.createNewComment(session, commentDto.getText(), user, post);
        }
    }

    private Post getPostById(int postId) {
        try (Session session = sessionFactory.openSession()) {
            return postDao.getPostById(session, postId);
        }

    }


    public int getCountUsers() {
        try (Session session = sessionFactory.openSession()) {
            return userDao.getCountUsers(session).intValue();
        }
    }

    public int getCountPosts() {
        try (Session session = sessionFactory.openSession()) {
            return  postDao.getCountPosts(session).intValue();
        }
    }

    public int getCountComments() {
        try (Session session = sessionFactory.openSession()) {
            return  commentDao.getCountComments(session).intValue();
        }
    }

    public User getUserByName(String userName) {
        try (Session session = sessionFactory.openSession()) {
            return  userDao.getUserByName(session, userName);
        }
    }

    public BigUserDTO getBigUserByName(String userName) {
        try (Session session = sessionFactory.openSession()) {
            return  userDao.getBigUserByName(session, userName);
        }
    }


}
