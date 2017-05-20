package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;
import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

/**
 * Created by lollipop on 16.07.2016.
 */
interface Command
{
    void execute() throws InterruptOperationException, NotEnoughMoneyException;
}
