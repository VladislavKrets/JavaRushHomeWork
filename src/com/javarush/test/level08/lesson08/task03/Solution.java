package com.javarush.test.level08.lesson08.task03;

import java.util.HashMap;

/* Одинаковые имя и фамилия
Создать словарь (Map<String, String>) занести в него десять записей по принципу «Фамилия» - «Имя».
Проверить сколько людей имеют совпадающие с заданным имя или фамилию.
*/

public class Solution
{
    public static HashMap<String, String> createMap()
    {
        //напишите тут ваш код
        HashMap<String, String> map = new HashMap<>();
        map.put("а", "б");
        map.put("б", "р");
        map.put("[", "б");
        map.put("в", "а");
        map.put("]", "а");
        map.put("г", "к");
        map.put("j", "а");
        map.put("т", "н");
        map.put("д", "л");
        map.put("л", "п");
        return map;

    }

    public static int getCountTheSameFirstName(HashMap<String, String> map, String name)
    {
        //напишите тут ваш код
        int count = 0;
        for (String key : map.values()) {
            if (name.equals(key)) count++;
        }
        return count;
    }

    public static int getCountTheSameLastName(HashMap<String, String> map, String lastName)
    {
        //напишите тут ваш код
        int count = 0;
        for (String value : map.keySet()) {
            if (lastName.equals(value)) count++;
        }
        return count;

    }
}
