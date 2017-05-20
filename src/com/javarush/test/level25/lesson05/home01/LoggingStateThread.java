package com.javarush.test.level25.lesson05.home01;

public class LoggingStateThread extends Thread
{
    private Thread thread;
    private State state;

    public LoggingStateThread(Thread thread)
    {
        super();
        this.thread = thread;
        state = thread.getState();
        setDaemon(true);
    }


    @Override
    public void run()
    {
        System.out.println(state);
        while (true)
        {

            if (thread.getState() != state)
            {
                state = thread.getState();
                System.out.println(state);
            }
            if (state == State.TERMINATED)
            {
                break;
            }
        }
    }
}
