package com.javarush.test.level39.lesson09.big01;

import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Solution {
    public static void main(String[] args) throws ParseException
    {
        LogParser logParser = new LogParser(Paths.get("C:\\Users\\lollipop\\Desktop\\javarush\\JavaRushHomeWork\\src\\com\\javarush\\test\\level39\\lesson09\\big01\\logs"));
        System.out.println(logParser.execute("get ip for user = \"Vasya Pupkin\""));
        System.out.println(logParser.execute("get ip for date \"13.09.2013 5:04:50\""));
        System.out.println(logParser.execute("get ip for date \"30.01.2014 12:56:22\""));
        System.out.println(logParser.execute("get ip for event = \"DONE_TASK\""));
        System.out.println(logParser.execute("get ip for status = \"FAILED\""));
        System.out.println(logParser.execute("get user for ip = \"12.12.12.12\""));
        System.out.println(logParser.execute("get user for date = \"13.09.2013 5:04:50\""));
        System.out.println(logParser.execute("get user for event = \"SOLVE_TASK\""));
        System.out.println(logParser.execute("get user for status = \"FAILED\""));
        System.out.println(logParser.execute("get date for ip = \"12.12.12.12\""));
        System.out.println(logParser.execute("get date for user = \"Amigo\""));
        System.out.println(logParser.execute("get date for event = \"LOGIN\""));
        System.out.println(logParser.execute("get date for status = \"ERROR\""));
        System.out.println(logParser.execute("get event for ip = \"146.34.15.5\""));
        System.out.println(logParser.execute("get event for user = \"Eduard Petrovich Morozko\""));
        System.out.println(logParser.execute("get event for date = \"30.01.2014 12:56:22\""));
        System.out.println(logParser.execute("get event for status = \"FAILED\""));
        System.out.println(logParser.execute("get status for ip = \"192.168.100.2\""));
        System.out.println(logParser.execute("get status for user = \"Vasya Pupkin\""));
        System.out.println(logParser.execute("get status for date = \"14.11.2015 07:08:01\""));
        System.out.println(logParser.execute("get status for event = \"WRITE_MESSAGE\""));


    }

}
