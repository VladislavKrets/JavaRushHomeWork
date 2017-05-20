package com.javarush.test.level18.lesson10.home09;

/* Файлы и исключения
Читайте с консоли имена файлов
Если файла не существует (передано неправильное имя файла), то
перехватить исключение FileNotFoundException, вывести в консоль переданное неправильное имя файла и завершить работу программы.
Закрыть потоки. Не использовать try-with-resources
Не используйте System.exit();
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        while (true)
        {
            try
            {
                String filename = reader.readLine();
                File file = new File(filename);
                if (!file.exists()) {
                    throw new FileNotFoundException(filename);
                }

            }
            catch (FileNotFoundException e)
            {
                System.out.println(e.getMessage());
                reader.close();
                break;

            }
        }
    }
}
