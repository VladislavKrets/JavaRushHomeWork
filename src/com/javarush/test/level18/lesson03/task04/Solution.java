package com.javarush.test.level18.lesson03.task04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream inputStream = new FileInputStream(reader.readLine());
        byte[] buffer = new byte[inputStream.available()];

        while (inputStream.available() > 0) {
            inputStream.read(buffer);
        }

        HashMap<Byte, Integer> map = new HashMap<>();
        int count = 0;
        for (byte a : buffer) {
            for (byte b : buffer) {
                if (a == b) {
                    count++;
                }
            }
            map.put(a, count);
            count = 0;
        }
        int numberofbytes = 0;
        for (Map.Entry<Byte, Integer> pair : map.entrySet()) {
            if (numberofbytes < pair.getValue()) numberofbytes = pair.getValue();
        }
        for (Map.Entry<Byte, Integer> pair : map.entrySet()) {
            if (numberofbytes > pair.getValue()) numberofbytes = pair.getValue();
        }
        for (Map.Entry<Byte, Integer> pair : map.entrySet()) {
            if (numberofbytes == pair.getValue()) System.out.print(pair.getKey() + " ");
        }
        reader.close();
        inputStream.close();
    }
}
