package com.skplanet.tutorial.testautomation.util;

import static com.skplanet.tutorial.testautomation.util.MapBuilder.*;
import static com.skplanet.tutorial.testautomation.util.Tuples.*;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

public class MapBuilderTest
{
    
    private final String KEY1 = "k1";
    private final String KEY2 = "k2";
    private final String VALUE1 = "v1";
    private final String VALUE2 = "v2";

    @Test
    public void initialBuilderBuildEmptyMap()
    {
        Map<String, String> map = MapBuilder.<String, String>builder().build();
        assertEquals(0, map.size());
    }
    
    @Test
    public void couldBuildNormalMapWithConstructor()
    {
        Map<String, String> map = builder(tuple(KEY1, VALUE1)).build();
        
        assertEquals(1, map.size());
        assertSame(VALUE1, map.get(KEY1));
        
        map = builder(tuple(KEY1, VALUE1), tuple(KEY2, VALUE2)).build();
        assertEquals(2, map.size());
        assertSame(VALUE2, map.get(KEY2));
    }
    
    @Test
    public void coudBuildMapUsingSetterWithTuple()
    {
        MapBuilder<String, String> builder = MapBuilder.<String, String>builder();
        builder.put(tuple(KEY1, VALUE1));

        Map<String, String> map = builder.build();
        assertEquals(1, map.size());
        assertSame(VALUE1, map.get(KEY1));
        
        map = builder.put(tuple(KEY2, VALUE2)).build();
        assertEquals(2, map.size());
        assertSame(VALUE2, map.get(KEY2));
    }
    
    @Test
    public void couldBuildMapUsingSetterWithKeyAndValue()
    {
        MapBuilder<String, String> builder = MapBuilder.<String, String>builder();
        builder.put(KEY1, VALUE1);
        
        Map<String, String> map = builder.build();
        assertEquals(1, map.size());
        assertSame(VALUE1, map.get(KEY1));
        
        map = builder.put(KEY2, VALUE2).build();
        assertEquals(2, map.size());
        assertSame(VALUE2, map.get(KEY2));
    }
    
    @Test
    public void couldBuildMapAtOnceWithSingleStaticMethod()
    {
        Map<String, String> map = map(tuple(KEY1, VALUE1));
        
        assertEquals(1, map.size());
        assertSame(VALUE1, map.get(KEY1));
        
        map = map(tuple(KEY1, VALUE1), tuple(KEY2, VALUE2));
        assertEquals(2, map.size());
        assertSame(VALUE2, map.get(KEY2));
    }
}