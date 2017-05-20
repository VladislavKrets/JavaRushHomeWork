package com.javarush.test.level19.lesson10.home03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/* Хуан Хуанович
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя день месяц год
где [имя] - может состоять из нескольких слов, разделенных пробелами, и имеет тип String
[день] - int, [месяц] - int, [год] - int
данные разделены пробелами

Заполнить список PEOPLE импользуя данные из файла
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Иванов Иван Иванович 31 12 1987
Вася 15 5 2013
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException{
        if (args == null || args.length != 1) return;
        BufferedReader fileReader = new BufferedReader(new FileReader(args[0]));

        while (fileReader.ready()) {
            String line = fileReader.readLine();
            String[] lineSplit = line.split(" ");

            StringBuilder builder = new StringBuilder("");
            for (int i = 0; i < lineSplit.length - 3; i++) {
                builder.append(lineSplit[i]).append(" ");
            }
            String name = builder.toString().trim();
            String day = lineSplit[lineSplit.length - 3];
            String month = lineSplit[lineSplit.length - 2];
            String year = lineSplit[lineSplit.length - 1];
            Calendar calendar = new GregorianCalendar(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(day));
            Date date = calendar.getTime();
            Person person = new Person(name, date);
            PEOPLE.add(person);
        }
        fileReader.close();
    }

}
