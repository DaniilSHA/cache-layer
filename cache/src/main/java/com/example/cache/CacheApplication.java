package com.example.cache;

import com.example.cache.lib_cache.User;
import com.example.cache.lib_cache.UserStorage;
import com.example.cache.tree_ex.TreeNode;
import com.example.cache.volatile_ex.Start;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
@EnableCaching
public class CacheApplication {

	public static void main(String[] args) throws InterruptedException
	{
		ConfigurableApplicationContext context = SpringApplication.run(CacheApplication.class, args);
		Start.start();
		TreeNode.start();

		UserStorage userStorage = context.getBean("userStorage", UserStorage.class);


		System.out.println(userStorage.readAll());
		System.out.println(userStorage.readAll());
		System.out.println(userStorage.readAll());

		System.out.println(userStorage.read(0));
		System.out.println(userStorage.read(0));

		userStorage.create(new User(userStorage.emitId(), "Ivan"));

		System.out.println(userStorage.read(0));
		System.out.println(userStorage.read(0));

		System.out.println(userStorage.readAll());
		System.out.println(userStorage.readAll());

		System.out.println(userStorage.read(3));
		System.out.println(userStorage.read(3));

		userStorage.update(3, "Roma");

		System.out.println(userStorage.read(3));
		System.out.println(userStorage.read(3));

		System.out.println(userStorage.readAll());
		System.out.println(userStorage.readAll());

		userStorage.delete(3);
		System.out.println(userStorage.readAll());
		System.out.println(userStorage.read(3));
	}

}
