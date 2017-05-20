package com.javarush.test.level19.lesson10.home07;

/* Длинные слова
В метод main первым параметром приходит имя файла1, вторым - файла2
Файл1 содержит слова, разделенные пробелом.
Записать через запятую в Файл2 слова, длина которых строго больше 6
Закрыть потоки. Не использовать try-with-resources

Пример выходных данных:
длинное,короткое,аббревиатура
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        FileWriter writer = new FileWriter(args[1]);
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> list1 = new ArrayList<>();
        while (reader.ready())
        {
            String a = reader.readLine();
            String[] words = a.split(" ");

            for (String s : words)
            {
                if (s.length() > 6)
                {
                    list.add(s);
                }
            }
        }
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            if (count == list.size() - 1)
            {
                list1.add(list.get(i));
                break;
            }
            list1.add(list.get(i) + ",");
            count++;
        }
        for (String s : list1) {
            writer.write(s);
        }
        reader.close();
        writer.close();
    }
}
