package com.javarush.test.level21.lesson16.big01;

import java.util.ArrayList;

/**
 * Created by lollipop on 10.06.2016.
 */
public class Hippodrome
{
    ArrayList<Horse> horses = new ArrayList<>();
    public static Hippodrome game;
    public static void main(String[] args)
    {
        game = new Hippodrome();
        Horse ala = new Horse("ala", 3, 0);
        Horse bala = new Horse("bala", 3, 0);
        Horse vala = new Horse("vala", 3, 0);
        game.getHorses().add(ala);
        game.getHorses().add(bala);
        game.getHorses().add(vala);
        game.run();
        game.printWinner();
    }

    public ArrayList<Horse> getHorses()
    {
        return horses;
    }
    public void move() {
        for (int i = 0; i < getHorses().size(); i++) {
            getHorses().get(i).move();
        }
    }
    public void run() {
        for (int i = 1; i <= 100; i++) {
            move();
            print();
            try
            {
                Thread.sleep(200);
            }
            catch (InterruptedException e)
            {
            }
        }
    }
    public void print() {
        for (int i = 0; i < getHorses().size(); i++)
        {
            getHorses().get(i).print();
        }
        System.out.println();
        System.out.println();
    }
    public Horse getWinner() {
        double distance = 0;
        for (int i = 0; i < getHorses().size(); i++) {
            if (getHorses().get(i).distance > distance) distance = getHorses().get(i).distance;
        }
        for (int i = 0; i < getHorses().size(); i++) {
            if (getHorses().get(i).distance == distance)
            {
                return getHorses().get(i);
            }
        }
        return null;
    }
    public void printWinner(){
        System.out.println("Winner is " + getWinner().getName()+ "!");
    }
}
