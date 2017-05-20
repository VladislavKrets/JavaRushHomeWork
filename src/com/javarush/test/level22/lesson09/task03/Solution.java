package com.javarush.test.level22.lesson09.task03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/* Составить цепочку слов
В методе main считайте с консоли имя файла, который содержит слова, разделенные пробелом.
В методе getLine используя StringBuilder расставить все слова в таком порядке,
чтобы последняя буква данного слова совпадала с первой буквой следующего не учитывая регистр.
Каждое слово должно участвовать 1 раз.
Метод getLine должен возвращать любой вариант.
Слова разделять пробелом.
В файле не обязательно будет много слов.

Пример тела входного файла:
Киев Нью-Йорк Амстердам Вена Мельбурн

Результат:
Амстердам Мельбурн Нью-Йорк Киев Вена
*/
public class Solution {
    public static void main(String[] args) throws IOException{
        //...
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fileReader = new BufferedReader(new FileReader(reader.readLine()));
        StringBuilder result = getLine(fileReader.readLine().split(" "));
        System.out.println(result.toString());
        reader.close();
        fileReader.close();
    }

    public static StringBuilder getLine(String... words) {
        if (words == null || words.length == 0) return new StringBuilder();

        Arrays.sort(words);
        ArrayList<String> list = new ArrayList<>(Arrays.asList(words));

        StringBuilder builder = new StringBuilder("");
        boolean isRight = true;
        while (true) {

            for (int i = 0; i < list.size(); i++) {
                if (i != list.size() - 1)
                {
                    if (!String.valueOf(list.get(i).charAt(list.get(i).length() - 1)).toLowerCase().equals(String.valueOf(list.get(i + 1).charAt(0)).toLowerCase()))
                    {
                        isRight = false;
                    }

                }
            }
            if (isRight) {
                for (String a : list) {
                    builder.append(a).append(" ");
                }
                break;
            }
            Collections.shuffle(list);
            isRight = true;
        }
        return builder.deleteCharAt(builder.length() - 1);
    }
}
