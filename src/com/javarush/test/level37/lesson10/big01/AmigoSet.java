package com.javarush.test.level37.lesson10.big01;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

/**
 * Created by lollipop on 30.07.2016.
 */
public class AmigoSet<E> extends AbstractSet<E> implements Serializable, Cloneable, Set<E>
{
    private static final Object PRESENT = new Object();
    private transient HashMap<E, Object> map;

    public AmigoSet()
    {
        map = new HashMap<E, Object>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        map = new HashMap<E, Object>((int) Math.max(16, collection.size() * .75f));
        this.addAll(collection);
    }

    @Override
    public boolean add(E e)
    {
        try
        {
            map.put(e,PRESENT);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    @Override
    public Iterator<E> iterator()
    {
        return map.keySet().iterator();
    }

    @Override
    public int size()
    {
        return map.keySet().size();
    }

    @Override
    public boolean isEmpty()
    {
        return map.keySet().isEmpty();
    }

    @Override
    public boolean contains(Object o)
    {
        return map.keySet().contains(o);
    }

    @Override
    public void clear()
    {
        map.keySet().clear();
    }

    @Override
    public boolean remove(Object o)
    {
        return map.keySet().remove(o);
    }

    @Override
    public Object clone()
    {
        try
        {
            AmigoSet<E> amigoSet = new AmigoSet<E>();
            amigoSet.addAll(this);
            return amigoSet;
        }
        catch (Exception e) {
            throw new InternalError();
        }

    }
    private void writeObject(java.io.ObjectOutputStream outputStream) throws IOException
    {
        outputStream.defaultWriteObject();
        outputStream.writeObject(map.size());
        for(E e:map.keySet()){
            outputStream.writeObject(e);
        }
        outputStream.writeObject(HashMapReflectionHelper.callHiddenMethod(map,"capacity"));
        outputStream.writeObject(HashMapReflectionHelper.callHiddenMethod(map,"loadFactor"));
        }
    private void readObject(java.io.ObjectInputStream inputStream) throws IOException, ClassNotFoundException
    {
        inputStream.defaultReadObject();
        int size = (int)inputStream.readObject();
        Set<E> set = new HashSet<>();
        for(int i =0;i<size;i++){
            set.add((E)inputStream.readObject());
        }
        int capacity = (int)inputStream.readObject();
        float loadFactor = (float)inputStream.readObject();
        map = new HashMap<>(capacity,loadFactor);
        for(E e:set){
            map.put(e,PRESENT);
        }
    }
}
