package com.skplanet.tutorial.testautomation.logger;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class LoggerFactoryTest {
 
    private static final String TEST_APPENDER_NAME = "appender1";
    private static final String TEST_LOGGER_NAME = "com.skplanet.tutorial";
    private static final String TEST_LOGGER_NAME_SHORT = "com.skplanet";
    private static final String TEST_LOGGER_NAME_LONG = "com.skplanet.tutorial.additional.package";
    private static final String TEST_MESSAGE = "a";

    @Test
    public void shouldBeInstantableAsSingleton()
    {
        assertNotNull(LoggerFactory.getLoggerFactory());
    }
    
    @Test
    public void shouldProduceLoggetThatIsUsingGivenAppender()
    {
        SpyAppender appender = new SpyAppender();
        LoggerFactory.setAppender(TEST_APPENDER_NAME, appender);
        LoggerFactory.setLogger(TEST_LOGGER_NAME, LogLevel.DEBUG, Arrays.asList(TEST_APPENDER_NAME));

        Logger logger = LoggerFactory.get(TEST_LOGGER_NAME);
        assertNotNull(logger);
        
        logger.debug(TEST_MESSAGE);
        assertEquals(TEST_MESSAGE, appender.lastMessage);
    }
    
    @Test
    public void shouldMatchWithLoggerName()
    {
        LoggerFactory.setAppender(TEST_APPENDER_NAME, new DummyAppender());
        LoggerFactory.setLogger(TEST_LOGGER_NAME, LogLevel.DEBUG, Arrays.asList(TEST_APPENDER_NAME));
        LoggerFactory.setLogger(TEST_LOGGER_NAME_SHORT, LogLevel.DEBUG, Arrays.asList(TEST_APPENDER_NAME));
        LoggerFactory.setLogger(TEST_LOGGER_NAME_LONG, LogLevel.DEBUG, Arrays.asList(TEST_APPENDER_NAME));
        
        CompositeLogger logger = (CompositeLogger) LoggerFactory.get(TEST_LOGGER_NAME);
        assertEquals(2, logger.loggers.size());
    }
}