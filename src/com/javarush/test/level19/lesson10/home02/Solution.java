package com.javarush.test.level19.lesson10.home02;

/* Самый богатый
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом

Для каждого имени посчитать сумму всех его значений
Вывести в консоль имена, у которых максимальная сумма
Имена разделять пробелом либо выводить с новой строки
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Петров 0.501
Иванов 1.35
Петров 0.85

Пример вывода:
Петров
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader fileReader = new BufferedReader(new FileReader(args[0]));
        String[] split;
        HashMap<String, Double> map = new HashMap<>();
        while (fileReader.ready()) {
            split = fileReader.readLine().split(" ");
            double value;
            if (map.containsKey(split[0])) {
                value = map.get(split[0]) + Double.parseDouble(split[1]);
                map.put(split[0], value);
            }
            else map.put(split[0], Double.valueOf(split[1]));
        }
        double count = 0.0;
        for (Map.Entry<String, Double> pair : map.entrySet()) {
            if (count < pair.getValue()) {
                count = pair.getValue();
            }
        }
        for (Map.Entry<String, Double> pair : map.entrySet()) {
            if (count == pair.getValue()) {
                System.out.println(pair.getKey());
            }
        }
        fileReader.close();
    }
}
