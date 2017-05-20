package com.javarush.test.level32.lesson08.home01;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by lollipop on 16.07.2016.
 */
class CustomInvocationHandler implements InvocationHandler
{

    private SomeInterfaceWithMethods someInterfaceWithMethods;

    CustomInvocationHandler(SomeInterfaceWithMethods someInterfaceWithMethods)
    {

        this.someInterfaceWithMethods = someInterfaceWithMethods;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        System.out.println(method.getName() + " in");
        Object result = method.invoke(someInterfaceWithMethods, args);

        System.out.println(method.getName() + " out");
        return result;
    }
}
