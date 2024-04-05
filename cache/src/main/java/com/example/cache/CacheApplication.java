package com.example.cache;

import com.example.cache.tree_ex.TreeNode;
import com.example.cache.volatile_ex.Start;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CacheApplication {

	public static void main(String[] args) throws InterruptedException
	{
		SpringApplication.run(CacheApplication.class, args);
		Start.start();
		TreeNode.start();
	}

}
