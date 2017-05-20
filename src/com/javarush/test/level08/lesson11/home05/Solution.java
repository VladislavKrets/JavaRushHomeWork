package com.javarush.test.level08.lesson11.home05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* Мама Мыла Раму. Теперь с большой буквы
Написать программу, которая вводит с клавиатуры строку текста.
Программа заменяет в тексте первые буквы всех слов на заглавные.
Вывести результат на экран.

Пример ввода:
  мама     мыла раму.

Пример вывода:
  Мама     Мыла Раму.
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        String[] space = s.split(" ");
        char chars[];
        for (int i = 0; i < space.length; i++) {
            chars = space[i].toCharArray();
            if (chars.length == 0) continue;
            space[i] = space[i].replaceFirst(String.valueOf(chars[0]), String.valueOf(Character.toUpperCase(chars[0])));

        }
        for (String a : space) {
            System.out.print(a + " ");

        }

        //напишите тут ваш код
    }


}
