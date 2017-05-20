package com.javarush.test.level31.lesson02.home02;

import java.io.File;
import java.io.IOException;
import java.util.*;

/* Находим все файлы
Реализовать логику метода getFileTree, который должен в директории root найти список всех файлов включая вложенные.
Используйте очередь, рекурсию не используйте.
Верните список всех путей к найденным файлам, путь к директориям возвращать не надо.
Путь должен быть абсолютный.
*/
public class Solution {
    public static void main(String[] args) throws IOException
    {
        getFileTree("c:\\");
    }
    public static List<String> getFileTree(String root) throws IOException {
        List<String> directoryList = new ArrayList<>();
        Queue<String> queue = new ArrayDeque<>();

        File directories;
        queue.add(root);
        while (!queue.isEmpty()) {
            String b = queue.element();

            directories = new File(b);
            if (directories.listFiles() == null) continue;
            for(File a : directories.listFiles()) {
                if (a.isDirectory()) {

                    queue.add(a.getAbsolutePath());
                }
                else {
                    directoryList.add(a.getAbsolutePath());

                }

            }
            queue.remove(b);
        }

        return directoryList;

    }
}
