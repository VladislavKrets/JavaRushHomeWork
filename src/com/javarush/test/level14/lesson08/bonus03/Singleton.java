package com.javarush.test.level14.lesson08.bonus03;

/**
 * Created by lollipop on 17.04.2016.
 */
public class Singleton
{
    private static Singleton c;
    public static Singleton getInstance() {
        if (c == null) {
            c = new Singleton();
        }
        return c;
    }
    private Singleton() {

    }
}
