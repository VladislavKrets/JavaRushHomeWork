package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.ad.Advertisement;
import com.javarush.test.level27.lesson15.big01.ad.StatisticAdvertisementManager;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lollipop on 05.08.2016.
 */
public class DirectorTablet
{
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

    public void printAdvertisementProfit()
    {
        double total = 0;
        for (Map.Entry<Date, Long> dayPr : StatisticEventManager.getInstance().dailyAdProfit().entrySet())
        {
            double am = dayPr.getValue() / 100.0;
            ConsoleHelper.writeMessage(String.format("%s - %.2f", dateFormat.format(dayPr.getKey()), am));
            total += am;
        }
        ConsoleHelper.writeMessage(String.format("Total - %.2f", total));
    }

    public void printCookWorkloading()
    {
        for (Map.Entry<Date, TreeMap<String, Integer>> cookMap : StatisticEventManager.getInstance().cookWork().entrySet())
        {
            ConsoleHelper.writeMessage(dateFormat.format(cookMap.getKey()));
            for (Map.Entry<String, Integer> dailycok : cookMap.getValue().entrySet())
            {
                ConsoleHelper.writeMessage(String.format("%s - %d min", dailycok.getKey(), dailycok.getValue()));
            }
            ConsoleHelper.writeMessage("");
        }
    }

    public void printActiveVideoSet()
    {
        Map<String, Integer> advertisementList = StatisticAdvertisementManager.getInstance().activeVideos();
        if (advertisementList == null || advertisementList.isEmpty()) return;
        for (Map.Entry<String, Integer> advertisement : advertisementList.entrySet())
        {
            ConsoleHelper.writeMessage(String.format("%s - %d", advertisement.getKey(), advertisement.getValue()));
        }
    }

    public void printArchivedVideoSet()
    {
        Map<String, Integer> advertisementList = StatisticAdvertisementManager.getInstance().notActiveVideos();
        if (advertisementList == null || advertisementList.isEmpty()) return;
        for (Map.Entry<String, Integer> advertisement : advertisementList.entrySet())
        {
            ConsoleHelper.writeMessage(String.format("%s", advertisement.getKey()));
        }
    }
}
