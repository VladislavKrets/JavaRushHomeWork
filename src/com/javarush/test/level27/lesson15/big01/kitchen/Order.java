package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.Tablet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by lollipop on 04.08.2016.
 */
public class Order extends Observable
{
    private Tablet tablet;
    protected List<Dish> dishes;
    private Cook cookedBy;

    public Order(Tablet tablet) throws IOException
    {
        this.tablet = tablet;
        this.dishes = initDishes();

    }

    public Cook getCookedBy()
    {
        return cookedBy;
    }

    public void setCookedBy(Cook cookedBy)
    {
        this.cookedBy = cookedBy;
    }

    public int getTotalCookingTime()
    {
        int number = 0;
        for (Dish a : dishes) {
            number += a.getDuration();
        }
        return number;
    }
    public boolean isEmpty() {
        return dishes.isEmpty();
    }

    public List<Dish> getDishes()
    {
        return dishes;
    }

    public Tablet getTablet()
    {
        return tablet;
    }

    protected List<Dish> initDishes() throws IOException
    {
        return ConsoleHelper.getAllDishesForOrder();
    }

    @Override
    public String toString()
    {
        return dishes.isEmpty() ? "" : String.format("Your order: %s of %s", dishes, tablet);
    }

}
