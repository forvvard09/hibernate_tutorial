package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.dao.CommentDao;
import org.example.dao.PostDao;
import org.example.dao.UserDao;
import org.example.dto.BigUserDTO;
import org.example.model.Post;
import org.example.utils.SqlUtil;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class Runner {
    public static void main(String[] args) {

        Server server = new Server(new UserDao(), new PostDao(), new CommentDao());

        //Statistics statistics = new Statistics();

       /* for (int i = 0; i < 10; i++) {
            server.createUser(new UserDto(SqlUtil.getRandomName(), SqlUtil.getRandomPsw()));
        }

        for (int i = 0; i < 30; i++) {
            int userId = SqlUtil.getRandomIdUser(server.getListUser());
            server.createPost(new PostDto(SqlUtil.getRandomPost(), userId));
        }

        for (int i = 0; i < 50; i++) {
            int userId = SqlUtil.getRandomIdUser(server.getListUser());
            int postId = SqlUtil.getRandomIdPost(server.getListPost());
            server.createComment(new CommentDto(SqlUtil.getRandomComment(), userId, postId));
        }*/

        SqlUtil.printInformations("Statistics", server.getCountUsers(), server.getCountPosts(), server.getCountComments());

        BigUserDTO user = server.getBigUserByName("Olgin Tolya");
        if (user != null) {
            System.out.println(user);
            List<Post> posts = user.getPosts();
            System.out.println(Arrays.asList(posts));
        } else {
            System.out.println("Пользователь не найден.");
        }


    }

}
