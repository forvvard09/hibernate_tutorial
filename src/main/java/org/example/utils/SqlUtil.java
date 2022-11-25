package org.example.utils;

import lombok.experimental.UtilityClass;
import org.example.dao.PostDao;
import org.example.model.Post;
import org.example.model.User;
import org.hibernate.Session;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@UtilityClass
public class SqlUtil {

    public static String getRandomName() {
        final List<String> testNamesLast = Arrays.asList("Alex", "Vlad", "John", "Anna", "Ivan", "Bob", "Oleg", "Yakov", "Arsen", "Fedor", "Nikol", "Artem", "Max", "Alina", "Tolya", "Yuri", "Petr", "Boris", "Nik", "Igor", "vlad", "Sasha", "Alex" );
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


    public Integer getRandomIdPost(List<Post> postList) {
        return postList.get(new Random().nextInt(postList.size())).getId();
    }

    public Integer getRandomIdUser(List<User> userList) {
        return userList.get(new Random().nextInt(userList.size())).getId();
    }

    public void printInformations(String name, int countUsers, int countPosts, int countComments) {
        String printText = String.format("Количество пользователей: %s%sКоличество постов: %s%sКоличество комментариев: %s", countUsers, System.lineSeparator(), countPosts, System.lineSeparator(), countComments);
        System.out.println("===========" + name + "=============");
        System.out.println(printText);
        System.out.println("------------------------------");
    }


}
