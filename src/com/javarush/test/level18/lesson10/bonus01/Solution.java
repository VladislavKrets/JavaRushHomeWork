package com.javarush.test.level18.lesson10.bonus01;

/* Шифровка
Придумать механизм шифровки/дешифровки

Программа запускается с одним из следующих наборов параметров:
-e fileName fileOutputName
-d fileName fileOutputName
где
fileName - имя файла, который необходимо зашифровать/расшифровать
fileOutputName - имя файла, куда необходимо записать результат шифрования/дешифрования
-e - ключ указывает, что необходимо зашифровать данные
-d - ключ указывает, что необходимо расшифровать данные
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        if (args.length != 3) return;
        if (args[0].equals("-e")) {
            String fileName = args[1];
            String fileOutputName = args[2];
            FileInputStream inputStream = new FileInputStream(fileName);
            FileOutputStream outputStream = new FileOutputStream(fileOutputName);
            while (inputStream.available() > 0) {
                outputStream.write(inputStream.read() + 1);
            }
            inputStream.close();
            outputStream.close();
        }
        else if (args[0].equals("-d")) {
            String fileName = args[1];
            String fileOutputName = args[2];
            FileInputStream inputStream = new FileInputStream(fileName);
            FileOutputStream outputStream = new FileOutputStream(fileOutputName);
            while (inputStream.available() > 0) {
                outputStream.write(inputStream.read() - 1);
            }
            inputStream.close();
            outputStream.close();
        }
    }
}
