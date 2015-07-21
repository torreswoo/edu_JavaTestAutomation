package com.skplanet.tutorial.testautomation.logger;

public class SpyAppender implements Appender
{
    public String lastMessage = null;

    public void append(String message)
    {
        this.lastMessage = message;
    }
}