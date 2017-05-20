package com.javarush.test.level30.lesson04.home01;

import java.util.concurrent.TransferQueue;

/**
 * Created by lollipop on 08.07.2016.
 */
public class Producer extends Thread
{
    private TransferQueue<ShareItem> queue;

    public Producer(TransferQueue<ShareItem> queue)
    {

        this.queue = queue;
    }

    @Override
    public void run()
    {
        try
        {
            for (int i = 1; i <= 9; i++)
            {

                queue.offer(new ShareItem("ShareItem-" + i, i));
                System.out.format("Элемент 'ShareItem-%d' добавлен%n", i);
                Thread.sleep(100);
                if (queue.hasWaitingConsumer()) System.out.println("Consumer в ожидании!");

            }
        }
        catch (InterruptedException e) {

    }
    }
}
