package com.javarush.test.level22.lesson05.task01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* Найти подстроку
Метод getPartOfString должен возвращать подстроку начиная с символа после 1-го пробела и до конца слова,
которое следует после 4-го пробела.
Пример: "JavaRush - лучший сервис обучения Java."
Результат: "- лучший сервис обучения"
На некорректные данные бросить исключение TooShortStringException (сделать исключением).
Сигнатуру метода getPartOfString не менять.
Метод main не участвует в тестировании.
*/
public class Solution {
    public static void main(String[] args) throws IOException, TooShortStringException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(getPartOfString(reader.readLine()));
    }
    public static String getPartOfString(String string) throws TooShortStringException
    {
        if (string == null || string.isEmpty()) throw new TooShortStringException();
        String[] split = string.split(" ");
        if (split.length < 5) throw new TooShortStringException();
        String line = "";
        for (int i = 1; i <= 4; i++) {
            if (i != 4)
            {
                line += split[i] + " ";
            }
            else line += split[i];
        }
        return line;
    }

    public static class TooShortStringException extends Throwable
    {
    }
}
