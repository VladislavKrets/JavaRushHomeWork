package com.javarush.test.level28.lesson06.home01;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lollipop on 03.07.2016.
 */
public class MyThread extends Thread
{
    static AtomicInteger priority = new AtomicInteger(1);

    public MyThread()
    {
        setPriority(priority.get());
        if (priority.get() == 10) priority.set(0);
        priority.incrementAndGet();
    }

    public MyThread(Runnable target)
    {
        super(target);
        setPriority(priority.get());
        if (priority.get() == 10) priority.set(0);
        priority.incrementAndGet();
    }

    public MyThread(ThreadGroup group, Runnable target)
    {
        super(group, target);
        setPriority(priority.get());
        if (priority.get() == 10) priority.set(0);
        priority.incrementAndGet();
    }

    public MyThread(String name)
    {
        super(name);
        setPriority(priority.get());
        if (priority.get() == 10) priority.set(0);
        priority.incrementAndGet();
    }

    public MyThread(ThreadGroup group, String name)
    {
        super(group, name);
        setPriority(priority.get());
        if (priority.get() == 10) priority.set(0);
        priority.incrementAndGet();
    }

    public MyThread(Runnable target, String name)
    {
        super(target, name);
        setPriority(priority.get());
        if (priority.get() == 10) priority.set(0);
        priority.incrementAndGet();
    }

    public MyThread(ThreadGroup group, Runnable target, String name)
    {
        super(group, target, name);
        setPriority(priority.get());
        if (priority.get() == 10) priority.set(0);
        priority.incrementAndGet();
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize)
    {
        super(group, target, name, stackSize);
        setPriority(priority.get());
        if (priority.get() == 10) priority.set(0);
        priority.incrementAndGet();
    }
}
