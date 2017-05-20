package com.javarush.test.level14.lesson08.bonus01;

import javax.management.JMException;
import javax.naming.NamingException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.rmi.NotBoundException;
import java.util.ArrayList;
import java.util.FormatterClosedException;
import java.util.List;

/* Нашествие эксепшенов
Заполни массив exceptions 10 различными эксепшенами.
Первое исключение уже реализовано в методе initExceptions.
*/

public class Solution
{
    public static List<Exception> exceptions = new ArrayList<Exception>();

    public static void main(String[] args)
    {
        initExceptions();

        for (Exception exception : exceptions)
        {
            System.out.println(exception);
        }
    }

    private static void initExceptions()
    {   //it's first exception
        try
        {
            float i = 1 / 0;

        } catch (Exception e)
        {
            exceptions.add(e);
        }

        //Add your code here
        exceptions.add(new NotBoundException());
        exceptions.add(new FileNotFoundException());
        exceptions.add(new NamingException());
        exceptions.add(new FormatterClosedException());
        exceptions.add(new JMException());
        exceptions.add(new BufferOverflowException());
        exceptions.add(new IOException());
        exceptions.add(new ArrayIndexOutOfBoundsException());
        exceptions.add(new NullPointerException());


    }
}
