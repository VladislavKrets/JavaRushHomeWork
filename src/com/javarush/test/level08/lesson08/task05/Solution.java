package com.javarush.test.level08.lesson08.task05;

import java.util.*;

/* Удалить людей, имеющих одинаковые имена
Создать словарь (Map<String, String>) занести в него десять записей по принципу «фамилия» - «имя».
Удалить людей, имеющих одинаковые имена.
*/

public class Solution
{

    public static HashMap<String, String> createMap()
    {
        //напишите тут ваш код

        HashMap<String, String> map = new HashMap<>();
        map.put("а", "а");
        map.put("б", "а");
        map.put("в", "б");
        map.put("г", "в");
        map.put("д", "в");
        map.put("е", "г");
        map.put("ж", "г");
        map.put("з", "г");
        map.put("и", "д");
        map.put("й", "е");

        return map;
    }

    public static void removeTheFirstNameDuplicates(HashMap<String, String> map)
    {
        ArrayList<String> doublicates = new ArrayList<>();
        int count = 0;
        for (String values : map.values()) {
            for (String values2 : map.values()) {
                if (values.equals(values2)) count++;
            }
            if (count > 1) doublicates.add(values);
            count = 0;
        }
        Set<String> set = new HashSet<>(doublicates);
        doublicates.clear();
        doublicates.addAll(set);
        HashMap<String, String> copy = new HashMap<>(map);
        for (Map.Entry<String, String> pair : copy.entrySet()) {
            for (String a : doublicates) {
                if (pair.getValue().equals(a))
                    map.remove(pair.getKey());
            }
        }
        //напишите тут ваш код

    }

    public static void removeItemFromMapByValue(HashMap<String, String> map, String value)
    {
        HashMap<String, String> copy = new HashMap<String, String>(map);
        for (Map.Entry<String, String> pair: copy.entrySet())
        {
            if (pair.getValue().equals(value))
                map.remove(pair.getKey());
        }
    }
}
