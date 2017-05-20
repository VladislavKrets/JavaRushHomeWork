package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;

import java.util.ResourceBundle;

/**
 * Created by lollipop on 16.07.2016.
 */
class InfoCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info_en");
    @Override
    public void execute()
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        for (Object manipulators : CurrencyManipulatorFactory.getAllCurrencyManipulators()) {
            if (!((CurrencyManipulator) manipulators).hasMoney()) {
                ConsoleHelper.writeMessage(res.getString("no.money"));
                continue;
            }
            ConsoleHelper.writeMessage(((CurrencyManipulator) manipulators).getCurrencyCode() + " - " + ((CurrencyManipulator) manipulators).getTotalAmount());
        }
    }
}
