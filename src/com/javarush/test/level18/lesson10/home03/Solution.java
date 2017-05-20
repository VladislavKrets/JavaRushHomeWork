package com.javarush.test.level18.lesson10.home03;

/* Два в одном
Считать с консоли 3 имени файла
Записать в первый файл содержимого второго файла, а потом дописать в первый файл содержимое третьего файла
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;


public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file01 = reader.readLine();
        String file02 = reader.readLine();
        String file03 = reader.readLine();
        FileOutputStream file1 = new FileOutputStream(file01);
        FileInputStream file2 = new FileInputStream(file02);
        byte[] buffer = new byte[file2.available()];
        while (file2.available() > 0) {
            int count = file2.read(buffer);
            file1.write(buffer, 0, count);
        }
        FileInputStream file3 = new FileInputStream(file03);
        file1 = new FileOutputStream(file01, true);
        buffer = new byte[file3.available()];
        while (file3.available() > 0) {
            int count = file3.read(buffer);
            file1.write(buffer, 0, count);
        }
        reader.close();
        file1.close();
        file2.close();
        file3.close();

    }
}