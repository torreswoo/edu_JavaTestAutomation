package com.skplanet.tutorial.testautomation.logger;

import java.util.List;

public class DefaultLogger implements Logger {
    
    List<Appender> appenders;
    LogLevel level;

    public DefaultLogger(LogLevel level, List<Appender> appenders)
    {
        super();
        this.level = level;
        this.appenders = appenders;
    }
    
    @Override
    public LogLevel getLevel()
    {
        return this.level;
    }

    @Override
    public void log(LogLevel level, String format, Object... args)
    {
        if(!isLogableLevel(level)) return;
        
        String message = String.format(format, args);
        for(Appender appender: this.appenders)
            appender.append(message);
    }

    private boolean isLogableLevel(LogLevel level)
    {
        return getLevel().ordinal() <= level.ordinal();
    }
}