package com.skplanet.tutorial.testautomation.logger;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class LoggerFactory {
    
    private static LoggerFactory instance;
    private Map<String, Appender> appenders = new ConcurrentHashMap<>();
    private Map<String, Logger> loggerMap = new ConcurrentHashMap<>();

    protected LoggerFactory() {
    }
    
    public static Logger get(String name)
    {
        return getLoggerFactory().getLogger(name);
    }
    
    public static Logger get(Class<?> clazz)
    {
        return get(clazz.getCanonicalName());
    }
    
    private Logger getLogger(String name)
    {
        List<Logger> collectedLoggers = new LinkedList<>();
        this.loggerMap.forEach((k, v) -> {
            if(k.startsWith(name)) collectedLoggers.add(v);
        });

        return new CompositeLogger(collectedLoggers);
    }
    
    public static void setAppender(String name, Appender appender)
    {
        LoggerFactory factory = getLoggerFactory();
        factory.appenders.put(name, appender);
    }
    
    public static void setLogger(String name, LogLevel level, List<String> appenderNames)
    {
        LoggerFactory factory = getLoggerFactory();
        List<Appender> appenders = new ArrayList<Appender>();
        for(String appenderName: appenderNames)
        {
            Appender appender = factory.appenders.get(appenderName);
            if(appender == null)
                throw new IllegalArgumentException("Could not found appender: "+appenderName);
            appenders.add(appender);
        }
        factory.loggerMap.put(name, new DefaultLogger(level, appenders));
    }
 
    static LoggerFactory getLoggerFactory()
    {
        if(instance == null)
        {
            synchronized (LoggerFactory.class)
            {
                if(instance == null)
                    instance = new LoggerFactory();
            }
        }
        return instance;
    }

}