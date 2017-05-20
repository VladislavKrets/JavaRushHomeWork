package com.javarush.test.level35.lesson10.bonus01;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Set;

/* ClassLoader - что это такое?
Реализуйте логику метода getAllAnimals.
Аргумент метода pathToAnimals - это абсолютный путь к директории, в которой хранятся скомпилированные классы.
Путь не обязательно содержит / в конце.
НЕ все классы наследуются от интерфейса Animal.
НЕ все классы имеют публичный конструктор без параметров.
Только для классов, которые наследуются от Animal и имеют публичный конструктор без параметров, - создать по одному объекту.
Добавить созданные объекты в результирующий сет и вернуть.
Метод main не участвует в тестировании.
*/
public class Solution {
    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals("C://pathToClasses/");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        if (!pathToAnimals.endsWith("/")) {
            pathToAnimals = pathToAnimals + "/";
        }
        final String path = pathToAnimals;
        Set<Animal> animals = new HashSet<>();

        ClassLoader loader = new ClassLoader()
        {
            @Override
            protected Class<?> findClass(String name) throws ClassNotFoundException
            {
                String url = "file:" + path + name + ".class";
                try
                {
                    URL myURL = new URL(url);
                    URLConnection connection = myURL.openConnection();
                    InputStream stream = connection.getInputStream();
                    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                    int data = stream.read();
                    while (data != -1) {
                        buffer.write(data);
                        data = stream.read();
                    }
                    stream.close();
                    byte[] classData = buffer.toByteArray();
                    return defineClass(classData, 0, classData.length);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                return null;
            }
        };
        File file = new File(pathToAnimals);
        File[] listFiles = file.listFiles(new FilenameFilter()
        {
            @Override
            public boolean accept(File dir, String name)
            {
                return name.endsWith(".class");
            }
        });

        for (File fileName : listFiles){
            try
            {

                String shortName = fileName.getName().substring(0,fileName.getName().length()-6);
                Class clz = loader.loadClass(shortName);
                Class[] classes = clz.getInterfaces();

                for(Class interfaces : classes){
                    if(interfaces.equals(Animal.class)){
                        Constructor[] constructors = clz.getConstructors();
                        for (Constructor c : constructors)
                        {
                            if(!Modifier.isAbstract(clz.getModifiers()) && Modifier.isPublic(c.getModifiers()) && c.getParameterTypes().length==0 )
                            {
                                animals.add((Animal)clz.newInstance());
                            }
                        }
                    }
                }

            }
            catch (ClassNotFoundException | IllegalAccessException | InstantiationException e)
            {
                e.printStackTrace();
            }
        }
        return animals;
    }
}
