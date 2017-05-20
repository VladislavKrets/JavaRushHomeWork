package com.javarush.test.level18.lesson10.bonus03;

/* Прайсы 2
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается с одним из следующих наборов параметров:
-u id productName price quantity
-d id
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-u  - обновляет данные товара с заданным id
-d  - производит физическое удаление товара с заданным id (все данные, которые относятся к переданному id)

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Solution {
    public static void main(String[] args) throws IOException{
        if (args == null || args.length == 0) return;
        if (args[0].equals("-u")) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String fileName = reader.readLine();
            BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
            String id = args[1];

            ArrayList<String> list = new ArrayList<>();
            while (fileReader.ready()) {
                list.add(fileReader.readLine());
            }

            String productName = "";

            for (int i = 2; i < args.length - 2; i++) {
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
            String idd = id;
            if (idd.length() < 8) {
                for (int i = idd.length(); i < 8; i++) {
                    idd += " ";
                }
            }
            else if (idd.length() > 8) {
                idd = idd.substring(0, 8);
            }

            String line = idd + productName + price + quantity;

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).substring(0, 8).trim().equals(id)) {
                    list.set(i, line);
                    break;
                }
            }
            reader.close();
            fileReader.close();
            FileWriter writer = new FileWriter(fileName);
            for (String a : list) {
                writer.write(a + "\n");
            }
            writer.close();
        } else if (args[0].equals("-d")) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String fileName = reader.readLine();
            BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
            ArrayList<String> list = new ArrayList<>();
            String id = args[1];
            while (fileReader.ready()) {
                list.add(fileReader.readLine());
            }
            Iterator<String> iterator = list.iterator();

            while (iterator.hasNext()) {
                String a = iterator.next();
                if (a.substring(0, 8).trim().equals(id)) {
                    iterator.remove();
                    break;
                }
            }
            reader.close();
            fileReader.close();
            FileWriter writer = new FileWriter(fileName);
            for (String a : list) {
                writer.write(a + "\n");
            }
            writer.close();
        }

    }
}
