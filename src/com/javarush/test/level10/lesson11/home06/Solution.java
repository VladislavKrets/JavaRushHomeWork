package com.javarush.test.level10.lesson11.home06;

/* Конструкторы класса Human
Напиши класс Human с 6 полями. Придумай и реализуй 10 различных конструкторов для него. Каждый конструктор должен иметь смысл.
*/

public class Solution
{
    public static void main(String[] args)
    {

    }

    public static class Human
    {
        //напишите тут ваши переменные и конструкторы
        String name;
        int age;
        int weight;
        int height;
        String nationality;
        String worker;
        public Human(String name) {
            this.name = name;
        }
        public Human(String name, int age) {
            this.name = name;
            this.age = age;
        }
        public Human(String name, int height, int weight) {
            this.name = name;
            this.height = height;
            this.weight = weight;
        }
        public Human(String name, String nationality) {
            this.name = name;
            this.nationality = nationality;
        }
        public Human(String name, String worker, int height) {
            this.name = name;
            this.worker = worker;
            this.height = height;
        }
        public Human(int height, String name) {
            this.height = height;
            this.name = name;
        }
        public Human(String name, String worker, String nationality) {
            this.name = name;
            this.worker = worker;
            this.nationality = nationality;
        }
        public Human(String name, int age, int weight, int height) {
            this.name = name;
            this.age = age;
            this.weight = weight;
            this.height = height;
        }
        public Human(String name, int age, int weight, int height, String nationality) {
            this.name = name;
            this.age = age;
            this.weight = weight;
            this.height = height;
            this.nationality = nationality;
        }
        public Human(String name, int age, int weight, int height, String nationality, String worker) {
            this.name = name;
            this.age = age;
            this.weight = weight;
            this.height = height;
            this.nationality = nationality;
            this.worker = worker;
        }
    }
}
