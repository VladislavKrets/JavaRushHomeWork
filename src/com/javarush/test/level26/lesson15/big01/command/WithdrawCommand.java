package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;
import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.Map;
import java.util.ResourceBundle;


/**
 * Created by lollipop on 16.07.2016.
 */
class WithdrawCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw_en");
    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        String code = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        while (true) {
            try
            {
                ConsoleHelper.writeMessage("Введите сумму:");
                String value = ConsoleHelper.readString();
                if (value.matches("\\-\\d+")) {
                    ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                }
                if (!value.matches("\\d+"))
                {
                    ConsoleHelper.writeMessage(res.getString("specify.amount"));
                    continue;
                }
                int integerValue = Integer.parseInt(value);
                if (!manipulator.isAmountAvailable(integerValue))
                {
                    ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
                    continue;
                }
                Map<Integer, Integer> result = manipulator.withdrawAmount(integerValue);
                for (Map.Entry<Integer, Integer> pair : result.entrySet())
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"), pair.getKey(), String.valueOf(pair.getValue())));
                break;
            }
            catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage(res.getString("not.enough.money"));
            }
        }
    }
}
