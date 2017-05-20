package com.javarush.test.level30.lesson04.home01;

import java.util.concurrent.TransferQueue;

/**
 * Created by lollipop on 08.07.2016.
 */
public class Consumer extends Thread
{
    private TransferQueue<ShareItem> queue;

    public Consumer(TransferQueue<ShareItem> queue)
    {

        this.queue = queue;
    }

    @Override
    public void run()
    {
        try
        {
            Thread.sleep(500);
            while (true) {

                System.out.println("Processing " + queue.take().toString());
            }
        }
        catch (InterruptedException e)
        {

        }

    }
}