package com.javarush.test.level18.lesson10.bonus02;

/* Прайсы
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается со следующим набором параметров:
-c productName price quantity
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-c  - добавляет товар с заданными параметрами в конец файла, генерирует id самостоятельно, инкрементируя максимальный id, найденный в файле

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        if (args == null || args.length == 0 || !args[0].equals("-c")) return;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        BufferedReader fileReader = new BufferedReader(new FileReader(fileName));

        int id = 0;
        while (fileReader.ready()) {
            int ids = Integer.parseInt(fileReader.readLine().substring(0, 8).trim());
            if (id < ids) {
                id = ids;
            }
        }
        id = id + 1;
        String ids = String.valueOf(id);
        if (ids.length() < 8) {
            for (int i = ids.length(); i < 8; i++) {
                ids += " ";
            }
        }
        else if (ids.length() > 8) {
            ids = ids.substring(0, 8);
        }
        String productName = "";

        for (int i = 1; i < args.length - 2; i++) {
            productName = productName +  args[i] + " ";
        }
        if (productName.length() < 30) {
            for (int i = productName.length(); i < 30; i++) {
                productName += " ";
            }
        }
        else if (productName.length() > 30) {
           productName = productName.substring(0, 30);
        }
        String price = args[args.length - 2];
        if (price.length() < 8) {
            for (int i = price.length(); i < 8; i++) {
                price += " ";
            }
        }
        else if (price.length() > 8) {
            price = price.substring(0, 8);
        }
        String quantity = args[args.length - 1];
        if (quantity.length() < 4) {
            for (int i = quantity.length(); i < 4; i++) {
                quantity += " ";
            }
        }
        else if (quantity.length() > 4) {
            quantity = quantity.substring(0, 4);
        }
        String line = ids + productName + price + quantity;
        FileOutputStream outputStream = new FileOutputStream(fileName, true);
        outputStream.write(line.getBytes());
        fileReader.close();
        outputStream.close();
    }
}
