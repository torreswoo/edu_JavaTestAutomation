package com.skplanet.tutorial.testautomation.logger;

import static com.skplanet.tutorial.testautomation.logger.LogLevel.*;

public interface Logger {
    
    public LogLevel getLevel();
    
    public void log(LogLevel level, String format, Object... args);
    
    public default void trace(String format, Object... args)
    {
        log(TRACE, format, args);
    }

    public default void debug(String format, Object... args)
    {
        log(DEBUG, format, args);
    }

    public default void info(String format, Object... args)
    {
        log(INFO, format, args);
    }

    public default void warn(String format, Object... args)
    {
        log(WARN, format, args);
    }

    public default void error(String format, Object... args)
    {
        log(ERROR, format, args);
    }
    
    public default void fatal(String format, Object... args)
    {
        log(ERROR, format, args);
    }
}