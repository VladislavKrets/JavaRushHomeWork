package com.javarush.test.level33.lesson15.big01;

import com.javarush.test.level33.lesson15.big01.strategies.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lollipop on 19.07.2016.
 */
public class Solution
{
    public static void main(String[] args)
    {
        testStrategy(new HashMapStorageStrategy(), 1000);
        testStrategy(new OurHashMapStorageStrategy(), 1000);
        testStrategy(new FileStorageStrategy(), 1000);
        testStrategy(new OurHashBiMapStorageStrategy(), 1000);
        testStrategy(new HashBiMapStorageStrategy(), 1000);
        testStrategy(new DualHashBidiMapStorageStrategy(), 1000);

    }
    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> set = new HashSet<>();
        for (String a : strings) {
            set.add(shortener.getId(a));
        }
        return set;
    }
    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> set = new HashSet<>();
        for (long a : keys) {
            set.add(shortener.getString(a));
        }
        return set;
    }
    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());
        Set<String> strings = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++)
        {
            strings.add(Helper.generateRandomString());
        }
        Shortener shortener = new Shortener(strategy);
        Date before = new Date();
        Set<Long> longs = getIds(shortener, strings);
        Date after = new Date();
        long time = after.getTime() - before.getTime();
        Helper.printMessage(String.valueOf(time));
        before = new Date();
        Set<String> set = getStrings(shortener, longs);
        after = new Date();
        time = after.getTime() - before.getTime();
        Helper.printMessage(String.valueOf(time));
        if (strings.size() == set.size()) {
            Helper.printMessage("Тест пройден.");
        }
        else Helper.printMessage("Тест не пройден.");
    }
}
