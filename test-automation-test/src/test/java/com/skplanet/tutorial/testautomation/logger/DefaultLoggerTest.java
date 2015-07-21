package com.skplanet.tutorial.testautomation.logger;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.function.Consumer;

import org.junit.Test;

public class DefaultLoggerTest 
{
    final String TEST_MESSAGE = "a";
    @Test
    public void shouldBeInstancable()
    {
        Logger logger = new DefaultLogger(LogLevel.DEBUG, Arrays.asList(new DummyAppender()));
        assertNotNull(logger);
    }
    
    @Test
    public void shoudLogTraceMessage()
    {
        assertEquals(TEST_MESSAGE, logSimpleMessage(LogLevel.TRACE, (logger)->logger.trace(TEST_MESSAGE)));
    }
    
    @Test
    public void shoudLogDebugMessage()
    {
        assertEquals(TEST_MESSAGE, logSimpleMessage(LogLevel.DEBUG, (logger)->logger.debug(TEST_MESSAGE)));
    }
    
    @Test
    public void shoudLogInfoMessage()
    {
        assertEquals(TEST_MESSAGE, logSimpleMessage(LogLevel.INFO, (logger)->logger.info(TEST_MESSAGE)));
    }
    
    @Test
    public void shoudLogWarnMessage()
    {
        assertEquals(TEST_MESSAGE, logSimpleMessage(LogLevel.WARN, (logger)->logger.warn(TEST_MESSAGE)));
    }
    
    @Test
    public void shoudLogErrorMessage()
    {
        assertEquals(TEST_MESSAGE, logSimpleMessage(LogLevel.ERROR, (logger)->logger.error(TEST_MESSAGE)));
    }
    
    @Test
    public void shouldNotLeaveLowerLevelLog() 
    {
        LogLevel[] levels = LogLevel.values();
        
        for(int i = 0; i < levels.length; i++)
        {
            SpyAppender appender = new SpyAppender();
            Logger logger = new DefaultLogger(levels[i], Arrays.asList(appender));
            LogMethod[] logMethods = {
                    logger::trace, logger::debug, logger::info, logger::warn, logger::error
            };
            
            for(int j = 0; j < logMethods.length; j++)
            {
                appender.lastMessage = null;
                logMethods[j].exec(TEST_MESSAGE);
                if(levels[i].ordinal() <= levels[j].ordinal())
                    assertEquals(TEST_MESSAGE, appender.lastMessage);
                else
                    assertNull(appender.lastMessage);
            }
        }
    }

    public String logSimpleMessage(LogLevel level, Consumer<Logger> f)
    {
        SpyAppender appender = new SpyAppender();
        Logger logger = new DefaultLogger(level, Arrays.asList(appender));
        f.accept(logger);
        
        return appender.lastMessage;
    } 
    
    static interface LogMethod 
    {
        public void exec(String format, Object... args);
    }
}