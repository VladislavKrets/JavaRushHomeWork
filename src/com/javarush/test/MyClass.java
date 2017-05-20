package com.javarush.test;

import javax.swing.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.awt.*;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
public class MyClass {
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Form2");
        frame.setSize(400, 600);

        frame.setLocationRelativeTo(new Component()
        {
            @Override
            public String getName()
            {
                return "Form";
            }
        });

        frame.setVisible(true);

    }
}

