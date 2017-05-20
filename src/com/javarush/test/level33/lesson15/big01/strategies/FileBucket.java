package com.javarush.test.level33.lesson15.big01.strategies;

import com.javarush.test.level33.lesson15.big01.ExceptionHandler;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by lollipop on 20.07.2016.
 */
public class FileBucket
{
    private Path path;

    public FileBucket()
    {
        try
        {
            path = Files.createTempDirectory(null);
            Files.deleteIfExists(path);
            Files.createFile(path).toFile().deleteOnExit();

        }
        catch (IOException e)
        {
            ExceptionHandler.log(e);
        }
    }
    public long getFileSize() {
        return path.toFile().length();
    }
    public void putEntry(Entry entry) {
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path.toFile()))){
            objectOutputStream.writeObject(entry);
        }
        catch (IOException e)
        {
            ExceptionHandler.log(e);
        }
    }
    public Entry getEntry() {
        if (getFileSize() == 0) return null;
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path.toFile())))
        {
            Entry entry = (Entry) objectInputStream.readObject();
            return entry;
        }
        catch (IOException | ClassNotFoundException e)
        {
            ExceptionHandler.log(e);
        }
        return null;
    }
    public void remove() {
        try
        {
            Files.delete(path);
        }
        catch (IOException e)
        {
            ExceptionHandler.log(e);
        }
    }
}
