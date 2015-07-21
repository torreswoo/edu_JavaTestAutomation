package com.skplanet.tutorial.testautomation.todo.web;

//import static java.lang.System.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.*;

import com.skplanet.tutorial.testautomation.todo.domain.Item;
import com.skplanet.tutorial.testautomation.todo.domain.TodoService;

@RestController
@RequestMapping("/todo/*")
public class TodoController {

    private static Logger logger = LoggerFactory.getLogger(TodoController.class);
    
    @Autowired
    private TodoService service;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Item add(@RequestParam("text") String text)
    {
        logger.debug("text: "+text);
        Item item = service.add(text);
        return item;
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Item> list()
    {
        logger.debug("Get todo list");
        List<Item> items = service.findAll();

        return items;
    }
    
    @RequestMapping(value="/done/{id}", method=RequestMethod.PUT)
    public Item checkDone(@PathVariable("id") long id) {
        logger.debug("Mark todo item #"+id+" as done");
        return service.checkDone(id);
    }
}