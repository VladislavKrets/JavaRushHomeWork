package com.javarush.test.level20.lesson10.bonus03;

import java.util.ArrayList;
import java.util.List;

/* Кроссворд
1. Дан двумерный массив, который содержит буквы английского алфавита в нижнем регистре.
2. Метод detectAllWords должен найти все слова из words в массиве crossword.
3. Элемент(startX, startY) должен соответствовать первой букве слова, элемент(endX, endY) - последней.
text - это само слово, располагается между начальным и конечным элементами
4. Все слова есть в массиве.
5. Слова могут быть расположены горизонтально, вертикально и по диагонали как в нормальном, так и в обратном порядке.
6. Метод main не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {

        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        List<Word> list = detectAllWords(crossword, "home", "same");
        for (Word word : list) {
            System.out.println(word.toString());
        }
        System.out.println("-------------------------------------");

        crossword = new int[][]{
                {'e', 'd', 'e', 'e', 'l', 'k', 'e', 'k'},
                {'u', 'm', 'a', 'm', 'e', 'm', 'k', 'k'},
                {'l', 'n', 'o', 'o', 'o', 'v', 'k', 'k'},
                {'e', 'm', 'o', 'h', 'o', 'm', 'e', 'k'},
                {'m', 'l', 'o', 'o', 'o', 'h', 'k', 'k'},
                {'m', 'm', 'p', 'm', 'r', 'm', 'k', 'k'},
                {'e', 'o', 'e', 'e', 'j', 'j', 'e', 'k'}
        };
        list = detectAllWords(crossword, "home");
        for (Word word : list) {
            System.out.println(word.toString());
        }
        System.out.println("-------------------------------------");

        crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        list = detectAllWords(crossword, "home", "same","klredf","kovhj","emoh","orgn","olns","kerpo","oprek","unpe","asu");
        for (Word word : list) {
            System.out.println(word.toString());
        }
        System.out.println("-------------------------------------");
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> list = new ArrayList<>();
        for (String word : words)
        {
            char[] chars = word.toCharArray();

                for (int i = 0; i < crossword.length; i++)
                {
                    for (int j = 0; j < crossword[i].length; j++)
                    {

                        if (crossword[i][j] == chars[0]) {
                            boolean flag = true;
                            if (i != crossword.length - 1 && crossword[i + 1][j] == chars[1]) {
                                for (int b = 2; b < chars.length; b++) {
                                    if (i + b < crossword.length && crossword[i + b][j] != chars[b]) {
                                        flag = false;
                                    } else if (i + b >= crossword.length) flag = false;
                                }
                                if (flag) {
                                    Word word1 = new Word(word);
                                    word1.startX = j;
                                    word1.startY = i;
                                    word1.endX = j;
                                    word1.endY = i + chars.length - 1;
                                    list.add(word1);
                                }
                            }
                            if (i != crossword.length - 1 && j != crossword[i].length - 1 && crossword[i + 1][j + 1] == chars[1]) {
                                for (int b = 2; b < chars.length; b++) {
                                    if (i + b < crossword.length && j + b < crossword[i].length && crossword[i + b][j + b] != chars[b]) {
                                        flag = false;
                                    } else if (i + b >= crossword.length || j + b >= crossword[i].length) {
                                        flag = false;
                                    }
                                }
                                if (flag) {
                                    Word word1 = new Word(word);
                                    word1.startX = j;
                                    word1.startY = i;
                                    word1.endX = j + chars.length - 1;
                                    word1.endY = i + chars.length - 1;
                                    list.add(word1);
                                }

                            } flag = true;
                            if (j != crossword[i].length - 1 && crossword[i][j + 1] == chars[1]) {
                                for (int b = 2; b < chars.length; b++) {
                                    if (j + b < crossword[i].length && crossword[i][j + b] != chars[b]) {
                                        flag = false;
                                    } else if (j + b >= crossword[i].length) {
                                        flag = false;
                                    }
                                }
                                if (flag) {
                                    Word word1 = new Word(word);
                                    word1.startX = j;
                                    word1.startY = i;
                                    word1.endX = j + chars.length - 1;
                                    word1.endY = i;
                                    list.add(word1);
                                }

                            }
                            flag = true;
                            if (i != 0 && crossword[i - 1][j] == chars[1]) {
                                for (int b = 2; b < chars.length; b++) {
                                    if (i - b >= 0 && crossword[i - b][j] != chars[b]) {
                                        flag = false;
                                    } else if (i - b < 0) {
                                        flag = false;
                                    }
                                }
                                if (flag) {
                                    Word word1 = new Word(word);
                                    word1.startX = j;
                                    word1.startY = i;
                                    word1.endX = j;
                                    word1.endY = i - chars.length + 1;
                                    list.add(word1);
                                }

                            }
                            flag = true;
                            if (i != 0 && j != 0 && crossword[i - 1][j - 1] == chars[1]) {
                                for (int b = 2; b < chars.length; b++) {
                                    if (i - b >= 0 && j - b >= 0 && crossword[i - b][j - b] != chars[b]) {
                                        flag = false;
                                    } else if (i - b < 0 || j - b < 0) {
                                        flag = false;
                                    }
                                }
                                if (flag) {
                                    Word word1 = new Word(word);
                                    word1.startX = j;
                                    word1.startY = i;
                                    word1.endX = j - chars.length + 1;
                                    word1.endY = i - chars.length + 1;
                                    list.add(word1);
                                }

                            }
                            flag = true;
                            if (j != 0 && crossword[i][j - 1] == chars[1]) {
                                for (int b = 2; b < chars.length; b++) {
                                    if (j - b >= 0 && crossword[i][j - b] != chars[b]) {
                                        flag = false;
                                    } else if (j - b < 0) {
                                        flag = false;
                                    }
                                }
                                if (flag) {
                                    Word word1 = new Word(word);
                                    word1.startX = j;
                                    word1.startY = i;
                                    word1.endX = j - chars.length + 1;
                                    word1.endY = i;
                                    list.add(word1);
                                }
                            }
                            flag = true;
                            if (i != crossword.length - 1 && j != 0 && crossword[i + 1][j - 1] == chars[1]) {
                                for (int b = 2; b < chars.length; b++) {
                                    if (i + b < crossword.length && j - b >= 0 && crossword[i + b][j - b] != chars[b]) {
                                        flag = false;
                                    } else if (j - b < 0 || i + b >= crossword.length) {
                                        flag = false;
                                    }
                                }
                                if (flag) {
                                    Word word1 = new Word(word);
                                    word1.startX = j;
                                    word1.startY = i;
                                    word1.endX = j - chars.length + 1;
                                    word1.endY = i + chars.length - 1;
                                    list.add(word1);
                                }
                            }
                            flag = true;
                            if (i != 0 && j != crossword[i].length - 1 && crossword[i - 1][j + 1] == chars[1]) {
                                for (int b = 2; b < chars.length; b++) {
                                    if (i - b >= 0 && j + b < crossword[i].length && crossword[i - b][j + b] != chars[b]) {
                                        flag = false;
                                    } else if (j + b >= crossword[i].length || i - b < 0) {
                                        flag = false;
                                    }
                                }
                                if (flag) {
                                    Word word1 = new Word(word);
                                    word1.startX = j;
                                    word1.startY = i;
                                    word1.endX = j + chars.length - 1;
                                    word1.endY = i - chars.length + 1;
                                    list.add(word1);
                                }
                            }
                        }
                    }

            }
        }
        return list;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
