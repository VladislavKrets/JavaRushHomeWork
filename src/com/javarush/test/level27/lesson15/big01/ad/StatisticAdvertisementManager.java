package com.javarush.test.level27.lesson15.big01.ad;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by lollipop on 05.08.2016.
 */
public class StatisticAdvertisementManager
{
    private static StatisticAdvertisementManager instance;

    private static AdvertisementStorage advertisementStorage = AdvertisementStorage.getInstance();

    private StatisticAdvertisementManager() {

    }

    public synchronized static StatisticAdvertisementManager getInstance()
    {
        if (instance == null) instance = new StatisticAdvertisementManager();
        return instance;
    }
    public Map<String, Integer> activeVideos() {
        TreeMap<String,Integer> result = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        for (Advertisement advertisement: advertisementStorage.list()){
            if (advertisement.getHits() > 0){
                result.put(advertisement.getName(), advertisement.getHits());
            }
        }
        return result;
    }
    public Map<String, Integer> notActiveVideos() {
        TreeMap<String,Integer> result = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        for (Advertisement advertisement: advertisementStorage.list()){
            if (advertisement.getHits() <= 0){
                result.put(advertisement.getName(), advertisement.getHits());
            }
        }
        return result;
    }
}
