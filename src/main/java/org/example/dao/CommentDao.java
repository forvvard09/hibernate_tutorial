package org.example.dao;

import org.example.model.Comment;
import org.example.model.Post;
import org.example.model.User;
import org.example.utils.SqlUtil;
import org.hibernate.Session;

import java.util.List;

public class CommentDao {
    public void createNewComment(Session session, User user, Post post) {
        session.beginTransaction();
        session.saveOrUpdate(new Comment(SqlUtil.getRandomComment(), user, post));
        session.getTransaction().commit();
    }

    public Long getCountComments(Session session) {
        return (Long) session.createQuery("select count(1) from Comment ").getSingleResult();
    }

    public List<Comment> getListcomments (Session session) {
        return session.createQuery("from Comment c order by c.Id ", Comment.class).list();
    }
}
