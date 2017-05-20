package com.javarush.test.level25.lesson05.task01;

/* Switch для нитей
Обработайте список нитей в зависимости от состояния:
1. Если нить еще не запущена, то запустите ее.
2. Если нить в ожидании, то прервите ее.
3. Если нить работает, то проверить маркер isInterrupted.
4. Если нить прекратила работу, то выведите в консоль ее приоритет.
Используйте switch.
*/
public class Solution {
    public static void processThreads(Thread... threads) {
        //implement this method - реализуйте этот метод
        for (Thread a : threads) {
            switch (a.getState()) {
                case NEW: a.start();
                    break;
                case WAITING: a.interrupt();
                    break;
                case TIMED_WAITING: a.interrupt();
                    break;
                case BLOCKED: a.interrupt();
                    break;
                case RUNNABLE: if (a.isInterrupted()) return;
                    break;
                case TERMINATED:
                    System.out.println(a.getPriority());
                    break;
            }
        }
    }
}
