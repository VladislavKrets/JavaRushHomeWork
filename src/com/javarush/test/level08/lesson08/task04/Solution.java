package com.javarush.test.level08.lesson08.task04;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/* Удалить всех людей, родившихся летом
Создать словарь (Map<String, Date>) и занести в него десять записей по принципу: «фамилия» - «дата рождения».
Удалить из словаря всех людей, родившихся летом.
*/

public class Solution
{

    public static HashMap<String, Date> createMap()
    {
        HashMap<String, Date> map = new HashMap<String, Date>();
        map.put("Stallone", new Date("AUGUST 1 1980"));
        map.put("Stallon", new Date("AUGUST 1 1980"));
        map.put("Stallo", new Date("AUGUST 1 1980"));
        map.put("Stall", new Date("AUGUST 1 1980"));
        map.put("Stal", new Date("AUGUST 1 1980"));
        map.put("Sta", new Date("AUGUST 1 1980"));
        map.put("St", new Date("AUGUST 1 1980"));
        map.put("S", new Date("AUGUST 1 1980"));
        map.put("k", new Date("AUGUST 1 1980"));
        map.put("gt", new Date("AUGUST 1 1980"));

        //напишите тут ваш код

        return map;

    }

    public static void removeAllSummerPeople(HashMap<String, Date> map)
    {
        //напишите тут ваш код
        HashMap<String, Date> copy = new HashMap<>(map);
        Date date;
        for (Map.Entry<String, Date> pair : copy.entrySet()) {
            date = pair.getValue();
            if (date.getMonth() == 5|| date.getMonth() == 6 || date.getMonth() == 7) {
                map.remove(pair.getKey());
            }
        }

    }
}
