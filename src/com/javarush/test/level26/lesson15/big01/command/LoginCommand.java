package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;
import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.ResourceBundle;

/**
 * Created by lollipop on 06.08.2016.
 */
public class LoginCommand implements Command
{
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login_en");
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String userCreditCardNumber = "";
        String userPin = "";

        while (true){
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            userCreditCardNumber = ConsoleHelper.readString();
            userPin = ConsoleHelper.readString();
            if (userCreditCardNumber != null && userPin != null
                    && validCreditCards.containsKey(userCreditCardNumber)) {
                if (userPin.equals(validCreditCards.getString(userCreditCardNumber))) {
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"), userCreditCardNumber));

                }
                else {
                    ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                    continue;
                }
            }
            else
            {
                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), userCreditCardNumber));
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                continue;
            }
            break;

        }
    }
}
