package com.javarush.test.level36.lesson08.task01;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.TreeSet;

/* Использование TreeSet
Первым параметром приходит имя файла: файл1.
файл1 содержит только буквы латинского алфавита, пробелы, знаки препинания, тире, символы перевода каретки.
Отсортировать буквы по алфавиту и вывести на экран первые 5 различных букв в одну строку без разделителей.
Если файл1 содержит менее 5 различных букв, то вывести их все.
Буквы различного регистра считаются одинаковыми.
Регистр выводимых букв не влияет на результат.
Закрыть потоки.

Пример 1 данных входного файла:
zBk yaz b-kN
Пример 1 вывода:
abkny

Пример 2 данных входного файла:
caAC
A, aB? bB
Пример 2 вывода:
abc

Подсказка: использовать TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        if (args == null || args.length == 0) return;
        TreeSet<String> strings = new TreeSet<>();
        FileReader reader = new FileReader(args[0]);
        while (reader.ready()) {
            strings.add(String.valueOf((char) reader.read()).toLowerCase());
        }
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            String a = iterator.next();
            if (!a.matches("\\w")) {
                iterator.remove();
            }
        }
        if (strings.size() >= 5) {
            for (int i = 0; i < 5; i++) {
                System.out.print(strings.pollFirst());
            }
        }
        else {
            for (int i = 0; i < strings.size(); i++) {
                System.out.print(strings.pollFirst());
            }
        }
    }
}
