package com.skplanet.tutorial.testautomation.logger;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

public class CompositeLoggerTest 
{
    final String TEST_MESSAGE = "a";
    @Test
    public void shouldBeInstancable()
    {
        Logger logger = new CompositeLogger(new ArrayList<Logger>());
        assertNotNull(logger);
    }

    @Test
    public void shouldDispatchLogToAnotherLogger() 
    {
        SpyAppender appender = new SpyAppender();
        List<Logger> loggers = Arrays.asList(new DefaultLogger(LogLevel.TRACE, Arrays.asList(appender)));
        Logger logger = new CompositeLogger(loggers);
        logger.debug(TEST_MESSAGE);
        assertEquals(TEST_MESSAGE, appender.lastMessage);
    }
    
    @Test
    public void shouldDispatchLogToAllOtherLoggers() 
    {
        SpyAppender appenderForDebug = new SpyAppender();
        SpyAppender appenderForWarn = new SpyAppender();
        List<Logger> loggers = Arrays.asList(
                new DefaultLogger(LogLevel.DEBUG, Arrays.asList(appenderForDebug)),
                new DefaultLogger(LogLevel.WARN, Arrays.asList(appenderForWarn)));
        Logger logger = new CompositeLogger(loggers);
        logger.info(TEST_MESSAGE);
        assertNull(appenderForWarn.lastMessage);
        assertEquals(TEST_MESSAGE, appenderForDebug.lastMessage);
    }
}