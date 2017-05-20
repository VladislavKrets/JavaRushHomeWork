package com.javarush.test.level19.lesson05.task02;

/* Считаем слово
Считать с консоли имя файла.
Файл содержит слова, разделенные знаками препинания.
Вывести в консоль количество слов "world", которые встречаются в файле.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fileReader = new BufferedReader(new FileReader(reader.readLine()));
        ArrayList<String> list = new ArrayList<>();
        while (fileReader.ready()) {
            list.add(fileReader.readLine());
        }
        reader.close();
        fileReader.close();
        String line = "";

        for (String a : list) {
            line += a + " ";
        }

        String[] buffer;
        buffer = line.split("\\p{Punct}+|\\p{Blank}+");
        int count = 0;

        for (String a : buffer) {
            if (a.equals("world")) count++;
        }
        System.out.println(count);
    }
}
