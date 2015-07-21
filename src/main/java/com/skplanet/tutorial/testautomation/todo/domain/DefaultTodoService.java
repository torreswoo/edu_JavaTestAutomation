package com.skplanet.tutorial.testautomation.todo.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultTodoService implements TodoService {

    @Autowired
    TodoRepository repository;

    @Override
    public Item add(String newTodoText)
    {
        long id = this.repository.add(newTodoText);
        return this.repository.findById(id);
    }

    @Override
    public List<Item> findAll()
    {
        return this.repository.findAll();
    }

    @Override
    public Item checkDone(long id) {
        Item oldItem = this.repository.findById(id);
        Item newItem = new Item(oldItem.getId(), true, oldItem.getCreatedDate(), LocalDateTime.now(), oldItem.getText());
        this.repository.update(newItem);
        return this.repository.findById(id);
    }
}
