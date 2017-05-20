package com.javarush.test.level14.lesson08.home05;

/**
 * Created by lollipop on 04.01.2016.
 */
public class Computer
{
   public Computer() {
       this.keyboard = new Keyboard();
       this.mouse = new Mouse();
       this.monitor = new Monitor();
   }



    private Keyboard keyboard;


    private Mouse mouse;


    private Monitor monitor;

    public Keyboard getKeyboard()
    {
        return keyboard;
    }

    public Mouse getMouse()
    {
        return mouse;
    }

    public Monitor getMonitor()
    {
        return monitor;
    }
}

