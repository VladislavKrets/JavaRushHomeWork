package com.javarush.test.level32.lesson15.big01;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by lollipop on 17.07.2016.
 */
public class HTMLFileFilter extends FileFilter
{
    @Override
    public boolean accept(File f)
    {
        if (!f.isDirectory())
        {
            String name = f.getName().toLowerCase();
            return name.endsWith(".html") || name.endsWith(".htm");
        }
        else if (f.isDirectory()) return true;
        return false;
    }

    @Override
    public String getDescription()
    {
        return "HTML и HTM файлы";
    }
}
