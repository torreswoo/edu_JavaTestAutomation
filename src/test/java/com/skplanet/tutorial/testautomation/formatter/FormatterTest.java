package com.skplanet.tutorial.testautomation.formatter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Formatter;
import java.util.MissingFormatArgumentException;

import org.junit.Test;

public class FormatterTest {

    public static void main(String[] args) throws Exception
    {
    	FormatterTest instance = new FormatterTest();
    	
    	// 인스턴스를 가지고 method를 실행 
    	instance.인자없이_생성_가능();
    	instance.빈포맷은_빈_문자열을_반환();
    	instance.긴_수를_콤마로_분리();
    	instance.실수를_자리수만큼_앞쪽에_0를_채우고_부호를_붙임();
    	instance.인자가_부족하면_예외를_발생();
    	
    	// reflection을 이용하여 test를 수행 => 실제로 JUnit이 해주는 일이다 
    	Method[] methods = instance.getClass().getDeclaredMethods();
    	for(Method method: methods){
    		if( (!method.getReturnType().equals(Void.TYPE)) || 
    				method.getParameterCount() > 0 ||
    				Modifier.isStatic(method.getModifiers()))
    			continue;
    		
    		try{
    			System.out.println(method.getName() + " - ");
    			method.invoke(instance);
    			System.out.println("O.K.");
    		}
    		catch(Throwable t){
    			System.out.println("Fail...");
    			t.printStackTrace();
    		}
    	}
    		
    }
    
    void 인자없이_생성_가능() 
    {
        try(Formatter target = new Formatter()) //java7: close해야하는 객체의 경우 finally안해되게 / 편
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
        String expected = "+01.111";
        String result = null;

        // 테스트 코드 필요 
        //when
        try(Formatter target = new Formatter())
        {
            result = target.format(format, value).toString();
        }

        //than
        if(!expected.equals(result))
            throw new AssertionError();
    }
    
    void 긴_수를_콤마로_분리()
    {
    	String expected = "12,345,678";
    	String format = "%,d";
    	int value = 12345678;
    	String result=null;
    	
    	//when
        try(Formatter target = new Formatter())
        {
            result = target.format(format, value).toString();
        }

        //than
        if(!expected.equals(result))
            throw new AssertionError();
    }
    
    void 인자가_부족하면_예외를_발생()
    {
    	try(Formatter target = new Formatter()){
    		target.format("%d").toString();
    		throw new AssertionError();
    	}
    	catch(MissingFormatArgumentException e){
    		
    	}
    }
}