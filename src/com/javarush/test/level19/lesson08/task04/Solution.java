package com.javarush.test.level19.lesson08.task04;

/* Решаем пример
В методе main подмените объект System.out написанной вами ридер-оберткой по аналогии с лекцией
Ваша ридер-обертка должна выводить на консоль решенный пример
Вызовите готовый метод printSomething(), воспользуйтесь testString
Верните переменной System.out первоначальный поток

Возможные операции: + - *
Шаблон входных данных и вывода: a [знак] b = c
Отрицательных и дробных чисел, унарных операторов - нет.

Пример вывода:
3 + 6 = 9
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream consolestream = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);
        System.setOut(stream);

        testString.printSomething();

        String result = outputStream.toString();
        System.setOut(consolestream);
        String[] a = result.split(" ", 4);
        int number1 = Integer.parseInt(a[0]);
        int number2 = Integer.parseInt(a[2]);
        int c = 0;
        if (a[1].equals("+")) {
            c = number1 + number2;
            System.out.print(result + c);
        }
        if (a[1].equals("-")) {
            c = number1 - number2;
            System.out.print(result + c);
        }
        if (a[1].equals("*")) {
            c = number1 * number2;
            System.out.print(result + c);
        }
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}

