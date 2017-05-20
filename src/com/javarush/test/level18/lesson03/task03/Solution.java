package com.javarush.test.level18.lesson03.task03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;/* Самые частые байты
Ввести с консоли имя файла
Найти байт или байты с максимальным количеством повторов
Вывести их на экран через пробел
Закрыть поток ввода-вывода
*/
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream inputStream = new FileInputStream(reader.readLine());
        byte[] bytes = new byte[inputStream.available()];
        while (inputStream.available() > 0) {
            inputStream.read(bytes);
        }
        HashMap<Byte, Integer> map = new HashMap<>();
        int count = 0;
        for (byte a : bytes) {
            for (byte b : bytes) {
                if (a == b) {
                    count++;
                }
            }
            map.put(a, count);
            count = 0;
        }
        count = 0;
        for (int a : map.values()) {
          if (a > count) {
              count = a;
          }
        }
        for (Map.Entry<Byte, Integer> pair : map.entrySet()) {
            if (pair.getValue() == count) {
                System.out.print(pair.getKey() + " ");
            }
        }
    }
}
