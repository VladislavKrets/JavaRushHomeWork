package com.javarush.test.level33.lesson15.big01.strategies;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by lollipop on 19.07.2016.
 */
public class Entry<key, value> implements Serializable
{
    final Long key;
    String value;
    Entry<key, value> next;
    final int hash;

    public Entry(int hash, Long key, String value, Entry<key, value> next)
    {
        this.key = key;
        this.value = value;
        this.next = next;
        this.hash = hash;
    }

    public Long getKey()
    {
        return key;
    }


    public String getValue()
    {
        return value;
    }

    @Override
    public int hashCode()
    {
        return hash ^ Objects.hashCode(value);
    }

    @Override
    public String toString()
    {
        return key + "=" + value;
    }
}
