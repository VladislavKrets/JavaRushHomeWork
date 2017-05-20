package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

/**
 * Created by lollipop on 16.07.2016.
 */
public class ConsoleHelper
{
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common_en");
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException
    {
        String a = null;
        try
        {
            a = reader.readLine();
            if (a.equalsIgnoreCase("EXIT")) throw new InterruptOperationException();
        }

        catch (IOException e)
        {

        }

        return a;
    }
    public static void printExitMessage() {
        writeMessage(res.getString("the.end"));
    }
    public static String askCurrencyCode() throws InterruptOperationException
    {
        String code = null;
        while (true)
        {
            writeMessage(res.getString("choose.currency.code"));
            code = readString();
            if (code.length() != 3) {
                writeMessage(res.getString("invalid.data"));
            }
            else break;
        }
        return code.toUpperCase();
    }
    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException
    {
        String[] split;
        while (true)
        {
            writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
            String code = readString();
            split = code.split(" ");
            if (split.length != 2 || !split[0].matches("\\d+") || !split[1].matches("\\d+")) {
                writeMessage(res.getString("invalid.data"));
            }
            else break;
        }

        return split;
    }
    public static Operation askOperation() throws InterruptOperationException
    {
        writeMessage(res.getString("choose.operation"));
        writeMessage(res.getString("operation.INFO"));
        writeMessage(res.getString("operation.DEPOSIT"));
        writeMessage(res.getString("operation.WITHDRAW"));
        writeMessage(res.getString("operation.EXIT"));
        Operation op = null;
        while (true)
        {
            int operation = Integer.parseInt(readString());
            try
            {
                op = Operation.getAllowableOperationByOrdinal(operation);
                break;
            }
            catch (IllegalArgumentException e) {
                writeMessage(res.getString("invalid.data"));
            }
        }
        return op;
    }
}
