package com.example.cache.kafka;

public class User
{
    private int id;
    private String name;

    public User(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public User()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void notify(String msg) {

        try
        {
            Thread.sleep(10);
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }

        System.out.println("thread: " + Thread.currentThread() + ", user: " + name +", id:" + id + " - was notify");
    }
}
