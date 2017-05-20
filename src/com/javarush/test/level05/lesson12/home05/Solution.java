package com.javarush.test.level05.lesson12.home05;

/* Вводить с клавиатуры числа и считать их сумму
Вводить с клавиатуры числа и считать их сумму, пока пользователь не введёт слово «сумма». Вывести на экран полученную сумму.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> list = new ArrayList<Integer>();
        int count = 0;
        for (int i = 0; true; i++) {
            String a = reader.readLine();
            if (!(a.equals("сумма"))) {
                list.add(Integer.parseInt(a));
            }
            else  {
                for (int ii = 0; ii < list.size(); ii++) {
                    count = list.get(ii) + count;
                }
                System.out.println(count);
                break;
            }
        }
    }
}
