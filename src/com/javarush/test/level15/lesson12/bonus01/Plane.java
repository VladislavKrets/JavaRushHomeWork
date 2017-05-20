package com.javarush.test.level15.lesson12.bonus01;

/**
 * Created by lollipop on 17.04.2016.
 */
public class Plane implements Flyable
{
    int countofpassengers;
    @Override
    public void fly()
    {

    }
    public Plane(int countofpassengers) {
        this.countofpassengers = countofpassengers;
    }
}
