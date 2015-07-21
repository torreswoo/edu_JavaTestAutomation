package com.skplanet.tutorial.testautomation.logger;

public enum LogLevel
{
    TRACE, DEBUG, INFO, WARN, ERROR, FATAL;
    
    public boolean isHigherLevel(LogLevel level) {
        return this.ordinal() >= level.ordinal();
    }
}