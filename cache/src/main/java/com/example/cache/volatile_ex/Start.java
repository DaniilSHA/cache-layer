package com.example.cache.volatile_ex;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Start
{
    public static void start() throws InterruptedException
    {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        SharedResource sharedResource = new SharedResource();

        System.out.println("Start: " + sharedResource.getCounter());

        for (int i = 0; i < 10; i++)
        {
            executorService.submit(() -> {
                for (int j = 0; j < 50000; j++)
                {
                    sharedResource.inc();
                }
            });
        }

        executorService.shutdown();
        while (!executorService.isTerminated()) {
            Thread.sleep(100);
        }
        System.out.println("End: " + sharedResource.getCounter());
    }
}
