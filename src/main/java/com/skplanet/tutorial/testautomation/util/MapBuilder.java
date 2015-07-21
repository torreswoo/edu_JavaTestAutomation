package com.skplanet.tutorial.testautomation.util;

import static com.skplanet.tutorial.testautomation.util.Tuples.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MapBuilder<K, V> {

    private Map<K, V> map = new ConcurrentHashMap<K, V>();

    @SafeVarargs
    public MapBuilder(Tuple2<K, V>... keyValues) {
        for(Tuple2<K, V> keyValue: keyValues)
            put(keyValue);
    }

    public MapBuilder<K, V> put(Tuple2<K, V> keyValue)
    {
        return put(keyValue.v1, keyValue.v2);
    }
    
    public MapBuilder<K, V> put(K key, V value)
    {
        this.map.put(key, value);
        return this;
    }

    public Map<K, V> build()
    {
        return map;
    }

    @SafeVarargs
    public static <K, V> MapBuilder<K, V> builder(Tuple2<K, V>... keyValues)
    {
        return new MapBuilder<K, V>(keyValues);
    }
    
    @SafeVarargs
    public static <K, V> Map<K, V> map(Tuple2<K, V>... keyValues)
    {
        return builder(keyValues).build();
    }
}