package com.javarush.test.level40.lesson06.task02;

/* Принадлежность точки многоугольнику
Дан многоугольник, заданный координатами своих вершин.
Ребра многоугольника не пересекаются.
Необходимо реализовать метод isPointInPolygon(Point point, List<Point> polygon), который проверит,
принадлежит ли переданная точка многоугольнику.
*/

import java.util.ArrayList;
import java.util.List;

class Point {
    public int x;
    public int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Solution {
    public static void main(String[] args) {
        List<Point> polygon = new ArrayList<>();
        polygon.add(new Point(0, 0));
        polygon.add(new Point(0, 10));
        polygon.add(new Point(10, 10));
        polygon.add(new Point(10, 0));

        System.out.println(isPointInPolygon(new Point(5, 5), polygon));
        System.out.println(isPointInPolygon(new Point(100, 100), polygon));
    }

    public static boolean isPointInPolygon(Point point, List<Point> polygon) {
        int count = 1;
        for (int i = 0; i < polygon.size(); i++)
        {
            Point firstPoint = polygon.get(i);
            Point secondPoint;
            if (i == polygon.size() - 1)
            {
                secondPoint = polygon.get(0);
            } else
            {
                secondPoint = polygon.get(i + 1);
            }
            count *= check(firstPoint, secondPoint, point);
        }
        //напишите тут ваш код
        return count != 1;
    }
    private static int check(Point a, Point b, Point middle)
    {
        long ax = a.x - middle.x;
        long ay = a.y - middle.y;
        long bx = b.x - middle.x;
        long by = b.y - middle.y;
        if (ay * by > 0)
            return 1;
        int s = Long.signum(ax * by - ay * bx);
        if (s == 0)
        {
            if (ax * bx <= 0)
                return 0;
            return 1;
        }
        if (ay < 0)
            return -s;
        if (by < 0)
            return s;
        return 1;
    }

}
