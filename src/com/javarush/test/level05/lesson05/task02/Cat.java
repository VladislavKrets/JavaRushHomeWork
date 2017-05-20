package com.javarush.test.level05.lesson05.task02;

/* Реализовать метод fight
Реализовать метод boolean fight(Cat anotherCat):
реализовать механизм драки котов в зависимости от их веса, возраста и силы.
Зависимость придумать самому. Метод должен определять, выиграли ли мы (this) бой или нет,
т.е. возвращать true, если выиграли и false - если нет.
Должно выполняться условие:
если cat1.fight(cat2) = true , то cat2.fight(cat1) = false
*/

public class Cat
{
    public String name;
    public int age;
    public int weight;
    public int strength;

    public Cat()
    {
    }

    public boolean fight(Cat anotherCat)
    {
        //напишите тут ваш код
        int ourscore = 0;
        int otherscore = 0;
        if (this.age < anotherCat.age) {
            ourscore -= 1;
            otherscore += 1;
        }
        else if (this.age > anotherCat.age){
            ourscore += 1;
            otherscore -=1;
        }
        if (this.weight < anotherCat.weight) {
            ourscore -= 1;
            otherscore += 1;
        }
        else if (this.weight > anotherCat.weight){
            ourscore += 1;
            otherscore -= 1;
        }
        if (this.strength < anotherCat.strength) {
            ourscore -= 1;
            otherscore += 1;
        }
        else if (this.strength < anotherCat.strength){
            ourscore += 1;
            otherscore -=1;
        }
        return ourscore < otherscore ? false : true;
    }
}
