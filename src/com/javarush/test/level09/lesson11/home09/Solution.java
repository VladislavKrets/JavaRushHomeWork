package com.javarush.test.level09.lesson11.home09;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* Десять котов
Создать класс кот – Cat, с полем «имя» (String).
Создать словарь Map(<String, Cat>) и добавить туда 10 котов в виде «Имя»-«Кот».
Получить из Map множество(Set) всех имен и вывести его на экран.
*/

public class Solution
{
    public static void main(String[] args)
    {
        Map<String, Cat> map = createMap();
        Set<Cat> set = convertMapToSet(map);
        printCatSet(set);
    }

    public static Map<String, Cat> createMap()
    {
        Map<String, Cat> hashMap = new HashMap<String, Cat>();

        hashMap.put("Mihail", new Cat("Mihail"));
        hashMap.put("Vasiliy", new Cat("Vasiliy"));
        hashMap.put("Alex", new Cat("Alex"));
        hashMap.put("Aleksandr", new Cat("Aleksandr"));
        hashMap.put("Anton", new Cat("Anton"));
        hashMap.put("kotofey", new Cat("kotofey"));
        hashMap.put("cat1", new Cat("cat1"));
        hashMap.put("cat2", new Cat("cat2"));
        hashMap.put("cat3", new Cat("cat3"));
        hashMap.put("cat4", new Cat("cat4"));

        return hashMap;
    }

    public static Set<Cat> convertMapToSet(Map<String, Cat> map)
    {
        Set<Cat> set = new HashSet<Cat>(map.values());
        return set;
    }

    public static void printCatSet(Set<Cat> set)
    {
        for (Cat cat:set)
        {
            System.out.println(cat);
        }
    }

    public static class Cat
    {
        private String name;

        public Cat(String name)
        {
            this.name = name;
        }

        public String toString()
        {
            return "Cat "+this.name;
        }
    }


}
