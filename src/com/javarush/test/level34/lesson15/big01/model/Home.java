package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

/**
 * Created by lollipop on 21.07.2016.
 */
public class Home extends GameObject
{
    public Home(int x, int y)
    {
        super(x, y, 2, 2);
    }

    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(Color.RED);
        graphics.drawOval(getX(), getY(), getWidth(), getHeight());
    }
}
