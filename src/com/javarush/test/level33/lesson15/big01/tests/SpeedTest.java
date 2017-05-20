package com.javarush.test.level33.lesson15.big01.tests;

import com.javarush.test.level33.lesson15.big01.Helper;
import com.javarush.test.level33.lesson15.big01.Shortener;
import com.javarush.test.level33.lesson15.big01.strategies.HashBiMapStorageStrategy;
import com.javarush.test.level33.lesson15.big01.strategies.HashMapStorageStrategy;
import com.javarush.test.level33.lesson15.big01.strategies.StorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lollipop on 20.07.2016.
 */
public class SpeedTest
{
    public long getTimeForGettingIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        Date before = new Date();
        for (String a : strings) {
            ids.add(shortener.getId(a));
        }
        Date after = new Date();
        return after.getTime() - before.getTime();
    }
    public long getTimeForGettingStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        Date before = new Date();
        for (long a : ids) {
            strings.add(shortener.getString(a));
        }
        Date after = new Date();
        return after.getTime() - before.getTime();
    }
    @Test
    public void testHashMapStorage() {
        StorageStrategy storageStrategy1 = new HashMapStorageStrategy();
        StorageStrategy storageStrategy2 = new HashBiMapStorageStrategy();

        Shortener shortener1 = new Shortener(storageStrategy1);
        Shortener shortener2 = new Shortener(storageStrategy2);

        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            origStrings.add(Helper.generateRandomString());
        }
        Set<Long> origLongs = new HashSet<>();
        long time1 = getTimeForGettingIds(shortener1, origStrings, origLongs);
        origLongs.clear();
        long time2 = getTimeForGettingIds(shortener2, origStrings, origLongs);
        Assert.assertTrue(time1 > time2);
        origStrings.clear();
        time1 = getTimeForGettingStrings(shortener1, origLongs, origStrings);
        origStrings.clear();
        time2 = getTimeForGettingStrings(shortener2, origLongs, origStrings);
        Assert.assertEquals(time1, time2, 5);

    }
}
