package com.skplanet.tutorial.testautomation.todo.domain;

import java.util.List;

public interface TodoService {

    public Item add(String newTodoText);

    public List<Item> findAll();

    public Item checkDone(long id);
}