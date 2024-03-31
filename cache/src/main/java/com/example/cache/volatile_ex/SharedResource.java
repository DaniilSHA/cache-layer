package com.example.cache.volatile_ex;

public class SharedResource
{
    private volatile int counter;

    public synchronized void inc() {
        counter++;

        //int local = counter
        //local = local + 1
        //counter = local
    }

    public int getCounter()
    {
        return counter;
    }
}
