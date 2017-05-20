package com.javarush.test.level31.lesson06.bonus01;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* Разархивируем файл
В метод main приходит список аргументов.
Первый аргумент - имя результирующего файла resultFileName, остальные аргументы - имена файлов fileNamePart.
Каждый файл (fileNamePart) - это кусочек zip архива. Нужно разархивировать целый файл, собрав его из кусочков.
Записать разархивированный файл в resultFileName.
Архив внутри может содержать файл большой длины, например, 50Mb.
Внутри архива может содержаться файл с любым именем.

Пример входных данных. Внутри архива находится один файл с именем abc.mp3:
C:/result.mp3
C:/pathToTest/test.zip.003
C:/pathToTest/test.zip.001
C:/pathToTest/test.zip.004
C:/pathToTest/test.zip.002
*/
public class Solution {
        public static void main(String[] args) throws IOException
        {
            File result = new File(args[0]);
            byte[] arr = new byte[1024];
            List<File> listFiles = new ArrayList<>();
            for(int i = 1; i < args.length; i++){
                listFiles.add(new File(args[i]));
            }
            Collections.sort(listFiles);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            for(File f : listFiles){
                FileInputStream fis = new FileInputStream(f);
                while(fis.read(arr)>-1){
                    baos.write(arr);
                    baos.flush();
                }
            }
            ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(baos.toByteArray()));
            FileOutputStream fosLoc = new FileOutputStream(result);
            ZipEntry ze = zis.getNextEntry();
            int count;
            while(ze!=null){
                while((count = zis.read(arr))>-1){
                    fosLoc.write(arr, 0, count);
                    fosLoc.flush();
                }
                ze = zis.getNextEntry();
            }
            fosLoc.close();
        }
}
