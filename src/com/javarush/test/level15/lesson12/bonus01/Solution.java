package com.javarush.test.level15.lesson12.bonus01;

/* Осваивание статического блока
1. В отдельных файлах создать классы Plane и Helicopter, реализующие интерфейс Flyable.
2. Класс Plane должен иметь 1 конструктор с параметром int - количество перевозимых пассажиров.
3. В статическом методе reset класса Solution:
3.1. Считать с консоли параметр типа String. Параметр может быть "plane" или "helicopter".
3.2. Если параметр равен "helicopter", то статическому объекту Flyable result присвоить объект класса Helicopter.
3.3. Если параметр равен "plane", то считать второй параметр типа int, статическому объекту Flyable result присвоить объект класса Plane.
4. В статическом блоке инициализировать Flyable result вызвав метод reset.
5. Закрыть поток ввода методом close().
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {

    static {
        //add your code here - добавьте код тут
        try
        {
            reset();
        }
        catch (Exception e)
        {
        }
    }

    public static Flyable result;

    public static void reset() throws Exception{
        //add your code here - добавьте код тут
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String a = reader.readLine();
        if (a.equals("helicopter")) {
            result = new Helicopter();
        }
        if (a.equals("plane")) {
            int b = Integer.parseInt(reader.readLine());
            result = new Plane(b);

        }
        reader.close();

    }

}
