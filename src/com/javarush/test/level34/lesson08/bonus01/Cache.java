package com.javarush.test.level34.lesson08.bonus01;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<K, V>();   //TODO add your code here

    public V getByKey(K key, Class<V> clazz) throws Exception {
        //TODO add your code here
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        else
        {
            cache.put(key, clazz.getConstructor(key.getClass()).newInstance(key));
            return cache.get(key);
        }
    }

    public boolean put(V obj)
    {
        //TODO add your code here
        try
        {
            Class clazz = obj.getClass();
            Method getKey = clazz.getDeclaredMethod("getKey");
            getKey.setAccessible(true);
            K k = (K) getKey.invoke(obj);
            cache.put(k, obj);
            return true;
        }
        catch (Exception e) {
            return false;
        }

    }

    public int size() {
        return cache.size();
    }
}
