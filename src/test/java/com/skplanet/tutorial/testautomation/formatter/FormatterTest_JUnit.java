package com.skplanet.tutorial.testautomation.formatter;

import static org.junit.Assert.assertEquals;

//2.junit단언문
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Formatter;
import java.util.MissingFormatArgumentException;

import org.junit.After;
import org.junit.Before;
//1.test method
import org.junit.Test;


//3.
import static org.hamcrest.CoreMatchers.*;

public class FormatterTest_JUnit {

	Formatter target;
	
	// 테스트설비! @After, @Before
	@Before
	public void setup(){
		target = new Formatter();
	}
	@After
	public void teardown(){
		target.close();
	}
	
	
	// 테스트 메서드! 
	@Test
    public void 인자없이_생성_가능() 
    {
		//hamcrest
        assertThat(target, is(notNullValue()));
    }
    
	@Test
    public void 빈포맷은_빈_문자열을_반환()
    {
        //given
        String format = "";
        String expected = "";
        String result = null;
        
        //when
        result = target.format(format).toString();

        //than
        //if(!expected.equals(result))
        //    throw new AssertionError();
        assertEquals(expected, result);
    }
    
	@Test
    public void 실수를_자리수만큼_앞쪽에_0를_채우고_부호를_붙임()
    {
        //given
        String format = "%+07.3f";
        Double value = 1.11111;
        String expected = "+01.111";
        String result = null;

        // 테스트 코드 필요 
        //when
        result = target.format(format, value).toString();
        
        //than
        //if(!expected.equals(result))
        //    throw new AssertionError();
        assertEquals(expected, result);
    }
    
	@Test
    public void 긴_수를_콤마로_분리()
    {
    	String expected = "12,345,678";
    	String format = "%,d";
    	int value = 12345678;
    	String result=null;
    	
    	//when
        result = target.format(format, value).toString();

        //than
        //if(!expected.equals(result))
        //    throw new AssertionError();
        assertEquals(expected, result);
    }
    
	@Test(expected=MissingFormatArgumentException.class)
    public void 인자가_부족하면_예외를_발생()
    {
    	try(Formatter target = new Formatter()){
    		target.format("%d").toString();
    		//throw new AssertionError();
    		fail(); // junit: 안잡히면 실패이므로 	
    	}
    }
}