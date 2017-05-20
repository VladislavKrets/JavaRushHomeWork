package com.javarush.test.level30.lesson06.home01;

import java.util.concurrent.RecursiveTask;

/**
 * Created by lollipop on 08.07.2016.
 */
public class BinaryRepresentationTask extends RecursiveTask<String>
{
    private int i;

    public BinaryRepresentationTask(int i)
    {
        this.i = i;

    }


    @Override
    protected String compute()
    {
        int a = i % 2;
        int b = i / 2;
        String result = String.valueOf(a);
        if (b > 0) {
            BinaryRepresentationTask binaryRepresentationTask = new BinaryRepresentationTask(b);
            binaryRepresentationTask.fork();
            return binaryRepresentationTask.join() + result;
        }
        return result;

    }
}
