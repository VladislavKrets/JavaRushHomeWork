package com.javarush.test.level34.lesson15.big01.model;

import com.javarush.test.level34.lesson15.big01.controller.EventListener;

import java.nio.file.Paths;;

/**
 * Created by lollipop on 21.07.2016.
 */
public class Model
{
    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private  LevelLoader levelLoader = new LevelLoader(Paths.get("C:/levels.txt"));
    public static final int FIELD_SELL_SIZE = 20;
    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }
    public GameObjects getGameObjects() {
        return gameObjects;
    }
    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }
    public void restart() {
        restartLevel(currentLevel);
    }
    public void startNextLevel() {
        currentLevel++;
        restart();
    }
    public void move(Direction direction) {
        Player player = gameObjects.getPlayer();
        if (checkWallCollision(player, direction)) return;
        if (checkBoxCollision(direction)) return;

        switch (direction) {
            case LEFT:
                player.move(-FIELD_SELL_SIZE, 0);
                break;
            case RIGHT:
                player.move(FIELD_SELL_SIZE, 0);
                break;
            case UP:
                player.move(0, -FIELD_SELL_SIZE);
                break;
            case DOWN:
                player.move(0, FIELD_SELL_SIZE);
                break;
        }
        checkCompletion();
    }
    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        for (Wall a : gameObjects.getWalls()) {
            if (gameObject.isCollision(a, direction)) return true;
        }
        return false;
    }
    public boolean checkBoxCollision(Direction direction) {
        boolean res = false;
        Player player = gameObjects.getPlayer();
        int x1 = player.getX();
        int y1 = player.getY();
        int count = FIELD_SELL_SIZE;
        switch (direction){
            case LEFT:
                x1 = x1-count;
                break;
            case RIGHT:
                x1 = x1+count;
                break;
            case UP:
                y1 = y1-count;
                break;
            case DOWN:
                y1 = y1+count;
        }
        GameObject  stoped = null;
        for (GameObject gameObject: gameObjects.getAll()){
            if (((x1 == gameObject.getX()) &&(y1 == gameObject.getY())) && !(gameObject instanceof Player)&& !(gameObject instanceof Home)){
                stoped = gameObject;
            }
        }

        if ((stoped == null)){
            return false;
        }
        if (stoped instanceof Box){
            Box stopedBox = (Box)stoped;
            if (checkWallCollision(stopedBox,direction)){
                return true;
            }
            for (Box box : gameObjects.getBoxes()){
                if(stopedBox.isCollision(box,direction)){
                    return true;
                }
            }
            switch (direction)
            {
                case LEFT:
                    stopedBox.move(-count, 0);
                    break;
                case RIGHT:
                    stopedBox.move(count, 0);
                    break;
                case UP:
                    stopedBox.move(0, -count);
                    break;
                case DOWN:
                    stopedBox.move(0, count);
            }
        }
        return false;
    }
    public void checkCompletion() {
        boolean yes = true;
        for(Home home : gameObjects.getHomes()){
            boolean currentyes = false;
            for (Box box: gameObjects.getBoxes()){
                if ((box.getX() == home.getX()) && (box.getY() == home.getY()))
                    currentyes = true;
            }
            if (!currentyes)yes = false;
        }
        if (yes)
            eventListener.levelCompleted(currentLevel);
    }

}
