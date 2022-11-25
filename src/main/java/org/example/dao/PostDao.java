package org.example.dao;

import org.example.model.Post;
import org.example.model.User;
import org.hibernate.Session;

import java.util.List;

public class PostDao {

    public List<Post> getListPosts(Session session) {
        return session.createQuery("from Post order by createdAt DESC", Post.class).list();
    }

    public void createNewPost(Session session, String postText, User user) {
        session.beginTransaction();
        session.saveOrUpdate(new Post(postText, user));
        session.flush();
        session.getTransaction().commit();
    }

    public Post getPostById(Session session, int postId) {
        Post post;
        session.beginTransaction();
        post = session.get(Post.class, postId);
        session.getTransaction().commit();
        return post;
    }

    public Long getCountPosts(Session session) {
        return (Long) session.createQuery("select count(1) from Post ").getSingleResult();
    }
}
