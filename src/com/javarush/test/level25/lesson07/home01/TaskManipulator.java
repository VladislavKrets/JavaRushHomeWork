package com.javarush.test.level25.lesson07.home01;

public class TaskManipulator implements Runnable, CustomThreadManipulator
{

    private String name;
    Thread thread;

    @Override
    public void run()
    {
        try
        {
            Thread.sleep(0);
            while (!thread.isInterrupted())
            {
                System.out.println(name);
                Thread.sleep(90);
            }

        }
        catch (InterruptedException e)
        {

        }
    }

    @Override
    public void start(String threadName)
    {
        this.name = threadName;
        thread = new Thread(this);
        thread.setName(name);
        thread.start();
    }

    @Override
    public void stop()
    {
        thread.interrupt();
    }
}
