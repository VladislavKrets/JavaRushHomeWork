package com.javarush.test.level07.lesson12.home03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* Максимальное и минимальное числа в массиве
Создать массив на 20 чисел. Заполнить его числами с клавиатуры. Найти максимальное и минимальное числа в массиве.
Вывести на экран максимальное и минимальное числа через пробел.
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int  maximum = 0;
        int  minimum = 0;
        int[] massiv = new int[20];
        //напишите тут ваш код
        for (int i = 0; i < massiv.length; i++) {
            massiv[i] = Integer.parseInt(reader.readLine());
        }
        for (int a : massiv) {
            if (maximum < a) {
                maximum = a;
            }
        }
        minimum = maximum;
        for (int a : massiv) {
            if (minimum > a) {
               minimum = a;
            }
        }


        System.out.print(maximum + " ");
        System.out.print(minimum);
    }
}
