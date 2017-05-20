package com.javarush.test.level18.lesson10.home08;

import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* Нити и байты
Читайте с консоли имена файлов, пока не будет введено слово "exit"
Передайте имя файла в нить ReadThread
Нить ReadThread должна найти байт, который встречается в файле максимальное число раз, и добавить его в словарь resultMap,
где параметр String - это имя файла, параметр Integer - это искомый байт.
Закрыть потоки. Не использовать try-with-resources
*/

public class Solution
{
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String fileName;
        while (true)
        {
            fileName = reader.readLine();
            if (fileName.equals("exit")) break;
            new ReadThread(fileName).start();
        }
        reader.close();

    }

    public static class ReadThread extends Thread
    {
        private String fileName;

        public ReadThread(String fileName)
        {
            //implement constructor body
            this.fileName = fileName;

        }

        @Override
        public void run()
        {
            FileInputStream inputStream = null;
            try
            {
                inputStream = new FileInputStream(fileName);
                Map <Integer, Integer> map = new HashMap<>();
                while (inputStream.available() > 0)
                {

                    Integer tmp = inputStream.read();
                    if (map.containsKey(tmp)) map.put(tmp, map.get(tmp) + 1);
                    else map.put(tmp, 1);
                }
                Integer maxValueInMap = Collections.max(map.values());
                for (Map.Entry<Integer, Integer> pair : map.entrySet())
                {
                    if (pair.getValue() == maxValueInMap)
                        synchronized (resultMap)
                        {
                            resultMap.put(fileName, pair.getKey());
                        }
                }
            }
            catch (IOException e)
            {
            }
        }
    }
}