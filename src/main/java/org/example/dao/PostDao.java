package org.example.dao;

import org.example.model.Post;
import org.example.model.User;
import org.hibernate.Session;

import java.util.List;

import static org.example.utils.SqlUtil.getRandomPost;

public class PostDao {

    public List<Post> getListPosts(Session session) {
        return session.createQuery("from Post order by created_at DESC", Post.class).list();
    }

    public void createNewPost(Session session, User user) {
        session.beginTransaction();
        session.saveOrUpdate(new Post(getRandomPost(), user));
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
