package com.skplanet.tutorial.testautomation.formatter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Formatter;
import java.util.MissingFormatArgumentException;

public class FormatterTest {
    
    public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        FormatterTest instance = new FormatterTest();
        Method[] methods = instance.getClass().getDeclaredMethods();
        for(Method method: methods) 
        {
            if ((!method.getReturnType().equals(Void.TYPE)) ||
                    method.getParameterCount() > 0 ||
                    Modifier.isStatic(method.getModifiers()))
                continue;
            
            try
            {
                System.out.print(method.getName() + " - ");
                method.invoke(instance);
                System.out.println("O.K.");
            } catch (Throwable t)
            {
                System.out.println("Fail...");
                t.printStackTrace();
            }
        }
    }
    
    void 인자없이_생성_가능() 
    {
        try(Formatter target = new Formatter())
        {
            return;
        }
        catch(Throwable t)
        {
            throw new AssertionError();
        }
    }
    
    void 빈포맷은_빈_문자열을_반환()
    {
        //given
        String format = "";
        String expected = "";
        String result = null;
        
        //when
        try(Formatter target = new Formatter())
        {
            result = target.format(format).toString();
        }

        //than
        if(!expected.equals(result))
            throw new AssertionError();
    }
    
    void 실수를_자리수만큼_앞쪽에_0를_채우고_부호를_붙임()
    {
        //given
        String format = "%+07.3f";
        Double value = 1.11111;
        String expeced = "+01.111";
        String result = null;
        
        //when
        try(Formatter target = new Formatter())
        {
            result = target.format(format, value).toString();
        }
        
        if(!expeced.equals(result))
            throw new AssertionError(result);
    }
    
    void 긴_수를_콤마로_분리()
    {
        try(Formatter target = new Formatter())
        {
            String expeced = "12,345,678";
            String result = target.format("%,d", 12345678).toString();
            if(!expeced.equals(result))
                throw new AssertionError(result);
        }
    }
    
    void 인자가_부족하면_예외를_발생()
    {
        try(Formatter target = new Formatter())
        {
            target.format("%d").toString();
            throw new AssertionError();
        }
        catch(MissingFormatArgumentException e)
        {
        }
    }
}