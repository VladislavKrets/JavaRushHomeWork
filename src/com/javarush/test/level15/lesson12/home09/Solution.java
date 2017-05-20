package com.javarush.test.level15.lesson12.home09;

/* Парсер реквестов
Считать с консоли URl ссылку.
Вывести на экран через пробел список всех параметров (Параметры идут после ? и разделяются &, например, lvl=15).
URL содержит минимум 1 параметр.
Если присутствует параметр obj, то передать его значение в нужный метод alert.
alert(double value) - для чисел (дробные числа разделяются точкой)
alert(String value) - для строк

Пример 1
Ввод:
http://javarush.ru/alpha/index.html?lvl=15&view&name=Amigo
Вывод:
lvl view name

Пример 2
Ввод:
http://javarush.ru/alpha/index.html?obj=3.14&name=Amigo
Вывод:
obj name
double 3.14
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    public static void main(String[] args) throws Exception
    {
        //add your code here
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] b = reader.readLine().split("&?+\\?+&?+");
        ArrayList<String> c = new ArrayList<>();
        for (String a : b)
        {
            Collections.addAll(c, a.split("&+"));
        }

        c.remove(0);

        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        for (String a : c)
        {
            if (a.contains("=") && a.split("=")[0].equals("obj"))
            {
                list.add(a.split("=")[0]);
                list2.add(a.split("=")[1]);
            } else if (a.contains("="))
            {
                list.add(a.split("=")[0]);
            } else list.add(a);
        }
        for (String a : list)
        {
            System.out.print(a + " ");
        }
        System.out.println();
        for (String a : list2)
        {
            if (a.matches("(\\d+\\.\\d+)|(\\d+)"))
            {
                alert(Double.parseDouble(a));
            } else alert(a);
        }
    }

    public static void alert(double value) {
        System.out.println("double " + value);
    }

    public static void alert(String value) {
        System.out.println("String " + value);
    }
}
