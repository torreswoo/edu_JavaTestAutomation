package com.skplanet.tutorial.testautomation.todo.domain;

import java.util.List;

public interface TodoRepository {

    public Item findById(long id);
    public List<Item> findAll();
    public long add(String newTodoText);
    public void update(Item newItem);
}
