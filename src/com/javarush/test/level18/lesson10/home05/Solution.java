package com.javarush.test.level18.lesson10.home05;

/* Округление чисел
Считать с консоли 2 имени файла
Первый файл содержит вещественные(дробные) числа, разделенные пробелом. Например, 3.1415
Округлить числа до целых и записать через пробел во второй файл
Закрыть потоки. Не использовать try-with-resources
Принцип округления:
3.49 - 3
3.50 - 4
3.51 - 4
-3.49 - -3
-3.50 - -3
-3.51 - -4
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader filereader = new BufferedReader(new FileReader(reader.readLine()));
        ArrayList<Integer> list = new ArrayList<>();
        FileWriter filewriter = new FileWriter(reader.readLine());
        String[] split;
        while (filereader.ready()) {
            split = filereader.readLine().split(" ");
            for (String a : split) {
                list.add((int) Math.round(Double.parseDouble(a)));
            }
        }
        for (int a : list) {
            filewriter.write(a + " ");
        }
        reader.close();
        filereader.close();
        filewriter.close();
    }
}
