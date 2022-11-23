package org.example.utils;

import lombok.experimental.UtilityClass;
import org.example.dao.PostDao;
import org.example.dao.UserDao;
import org.example.model.Post;
import org.example.model.User;
import org.hibernate.Session;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@UtilityClass
public class SqlUtil {

    public static String getRandomName() {
        final List<String> testNamesLast = Arrays.asList("Alex", "Vlad", "John", "Anna", "Ivan", "Bob", "Oleg", "Yakov", "Arsen", "Fedor", "Nikol", "Artem");
        final List<String> testNameSurname = Arrays.asList("Alekseev", "Vladimirov", "Yogov", "Annaniev", "Ivanov", "Bobov", "Egorov", "Yakovlev", "Zaharyan", "Olgin", "Nikolaev", "Artemov");
        return String.format("%s %s", testNameSurname.get(new Random().nextInt(testNameSurname.size())), testNamesLast.get(new Random().nextInt(testNamesLast.size())));
    }

    public static String getRandomPsw() {
        return String.format("pasw-%s", new Random().nextInt(100000));
    }

    public static String getRandomPost() {
        return String.format("Test post №: %9d", new Random().nextInt(1000000000));
    }

    public static String getRandomComment() {
        return String.format("Test comment №: %5d", new Random().nextInt(100000));
    }



    public Integer getRandomIdUser(Session session) {
        List<User> users =  new UserDao().getListUsers(session);
        return users.get(new Random().nextInt(users.size())).getId();
    }

    public Integer getRandomIdPost(Session session) {
        List<Post> posts =  new PostDao().getListPosts(session);
        return posts.get(new Random().nextInt(posts.size())).getId();
    }


}
