package com.example.cache.lib_cache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserStorage
{
    private static int idCounter = 0;

    private List<User> users = new ArrayList<>();

    {
        users.add(new User(idCounter++, "Petr"));
        users.add(new User(idCounter++, "Danil"));
        users.add(new User(idCounter++, "Oleg"));
    }

    public int emitId() {
        return idCounter++;
    }

    @CacheEvict(value = "users", key = "'readAll'")
    @CachePut(value = "users", key = "#user.id")
    public User create(User user) {
        System.out.println("create");
        users.add(user);
        return user;
    }

    @CacheEvict(value = "users", key = "'readAll'")
    @CachePut(value = "users", key = "#id")
    public User update(int id, String name) {
        System.out.println("update");
        User updateUser = users.stream().filter(user -> user.id == id).findFirst().orElseThrow();
        updateUser.setName(name);
        return updateUser;
    }


    @Caching(evict = {@CacheEvict(value = "users", key = "'readAll'"), @CacheEvict(value = "users", key = "#id")})
    public void delete(int id) {
        System.out.println("delete");
        User deleteUser = users.stream().filter(user -> user.id == id).findFirst().orElseThrow();
        users.remove(deleteUser);
    }

    @Cacheable(value = "users", key = "#id")
    public User read(int id) {
        System.out.println("read");
        return users.stream().filter(user -> user.id == id).findFirst().orElseThrow();
    }

    @Cacheable(value = "users", key = "'readAll'")
    public List<User> readAll() {
        System.out.println("readAll");
        return new ArrayList<>(users);
    }

    // Map<Object, Object> map;
    // "readAll" -> List<User>
    // 0 -> User[0]
    // 1 -> User[1]

}
