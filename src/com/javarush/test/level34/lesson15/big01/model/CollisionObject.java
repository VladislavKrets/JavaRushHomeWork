package com.javarush.test.level34.lesson15.big01.model;

/**
 * Created by lollipop on 21.07.2016.
 */
public abstract class CollisionObject extends GameObject
{
    public CollisionObject(int x, int y)
    {
        super(x, y);
    }
    public boolean isCollision(GameObject gameObject, Direction direction) {
        int newX = -1;
        int newY = -1;
        switch (direction) {
            case UP: newX = getX(); newY = getY() - Model.FIELD_SELL_SIZE; break;
            case DOWN: newX = getX(); newY = getY() + Model.FIELD_SELL_SIZE; break;
            case LEFT: newX = getX() - Model.FIELD_SELL_SIZE; newY = getY(); break;
            case RIGHT: newX = getX() + Model.FIELD_SELL_SIZE; newY = getY(); break;
        }
        return newX == gameObject.getX() && newY == gameObject.getY();
    }
}
