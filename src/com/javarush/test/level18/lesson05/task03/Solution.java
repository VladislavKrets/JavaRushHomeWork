package com.javarush.test.level18.lesson05.task03;

/* Разделение файла
Считать с консоли три имени файла: файл1, файл2, файл3.
Разделить файл1 по следующему критерию:
Первую половину байт записать в файл2, вторую половину байт записать в файл3.
Если в файл1 количество байт нечетное, то файл2 должен содержать бОльшую часть.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();
        String file3 = reader.readLine();

        FileInputStream inputStream1 = new FileInputStream(file1);
        FileOutputStream outputStream2 = new FileOutputStream(file2);
        FileOutputStream outputStream3 = new FileOutputStream(file3);

        byte[] buffer1 = new byte[inputStream1.available()];

        while (inputStream1.available() > 0) {
            inputStream1.read(buffer1);
        }

        byte[] buffer2;
        byte[] buffer3;

        if (buffer1.length % 2 == 0) {
            buffer2 = new byte[buffer1.length / 2];
            buffer3 = new byte[buffer1.length / 2];
        }
        else {
            buffer2 = new byte[(buffer1.length / 2) + 1];
            buffer3 = new byte[buffer1.length / 2];

        }
        for (int i = 0; i < buffer2.length; i++) {
            buffer2[i] = buffer1[i];
        }
        for (int i = buffer3.length + 1, j = 0; i < buffer1.length; i++, j++) {
            buffer3[j] = buffer1[i];
        }
        outputStream2.write(buffer2);
        outputStream3.write(buffer3);

        reader.close();
        inputStream1.close();
        outputStream2.close();
        outputStream3.close();
    }
}
