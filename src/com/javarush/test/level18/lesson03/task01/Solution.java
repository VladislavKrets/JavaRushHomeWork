package com.javarush.test.level18.lesson03.task01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;



/* Максимальный байт
Ввести с консоли имя файла
Найти максимальный байт в файле, вывести его на экран.
Закрыть поток ввода-вывода
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        String filename = reader.readLine();
        FileInputStream inputStream = new FileInputStream(filename);
        while (inputStream.available() > 0) {
            int data = inputStream.read();
            arrayList.add(data);
        }
        Collections.sort(arrayList);
        System.out.println(arrayList.get(arrayList.size() - 1));
        reader.close();
        inputStream.close();
    }
}
