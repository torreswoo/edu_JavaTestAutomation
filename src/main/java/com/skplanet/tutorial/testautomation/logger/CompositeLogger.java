package com.skplanet.tutorial.testautomation.logger;

import java.util.List;

public class CompositeLogger implements Logger {
    
    LogLevel level;
    List<Logger> loggers;
    
    public CompositeLogger(List<Logger> loggers)
    {
        super();
        this.loggers = loggers;
        updateLogLevel(loggers);
    }
    
    @Override
    public LogLevel getLevel()
    {
        return this.level;
    }
    
    @Override
    public void log(LogLevel level, String format, Object... args)
    {
        for(Logger logger: this.loggers) {
            logger.log(level, format, args);
        }
    }
    
    private void updateLogLevel(List<Logger> loggers)
    {
        LogLevel lowestLevel = LogLevel.FATAL;
        for(Logger logger: loggers) {
            if(lowestLevel.ordinal() > logger.getLevel().ordinal())
                lowestLevel = logger.getLevel();
        }        
        this.level = lowestLevel;
    }
}