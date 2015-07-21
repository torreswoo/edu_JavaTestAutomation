package com.skplanet.tutorial.testautomation.todo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;

import com.skplanet.tutorial.testautomation.todo.TodoApplication;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TodoApplication.class)
@WebAppConfiguration
public class TestAutomationApplicationTests {

    @Test
    public void contextLoads()
    {
        
    }

}
