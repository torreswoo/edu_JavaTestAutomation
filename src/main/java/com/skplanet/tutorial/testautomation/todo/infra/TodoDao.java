package com.skplanet.tutorial.testautomation.todo.infra;

import static com.skplanet.tutorial.testautomation.util.Tuples.*;
import static com.skplanet.tutorial.testautomation.util.MapBuilder.*;

import java.sql.*;
import java.util.*;
import java.util.function.Function;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.skplanet.tutorial.testautomation.todo.domain.*;

@Repository
public class TodoDao implements TodoRepository
{
    private NamedParameterJdbcTemplate jt;

    @Autowired
    public void setDataSource(DataSource ds)
    {
        this.jt = new NamedParameterJdbcTemplate(ds);
    }
    
    public Item findById(long id)
    {
        return jt.queryForObject(
                "SELECT id, status, created_date, done_date, text FROM todos WHERE id=:id",
                map(tuple("id", id)), this::mapRow);
    }
    
    @Override
    public List<Item> findAll()
    {
        return jt.query("SELECT id, status, created_date, done_date, text FROM todos ORDER BY id ASC",
                map(), this::mapRow);
    }

    @Override
    public long add(String newTodoText)
    {
        jt.update("INSERT INTO todos (text) VALUES(:text)", map(tuple("text", newTodoText)));
        return getLastId();
    }
    
    @Override
    public void update(Item item)
    {
        jt.update("UPDATE todos SET status=:status, done_date=:done_date, text=:text WHERE id=:id",
                map(tuple("id", item.getId()), tuple("status", item.isDone()),
                    tuple("done_date", ifNotNull(item.getDoneDate(), d -> Timestamp.valueOf(d), null)), 
                    tuple("text", item.getText())));
    }

    private Item mapRow(ResultSet rs, int rownum) throws SQLException
    {
        return new Item(rs.getLong("id"), 
                rs.getBoolean("status"), 
                rs.getTimestamp("created_date").toLocalDateTime(), 
                ifNotNull(rs.getTimestamp("done_date"), x -> x.toLocalDateTime(), null),
                rs.getString("text"));
        
    }

    private long getLastId()
    {
        return jt.queryForObject("SELECT IDENTITY()", map(), Long.class);
    }
    
    private static <T, R> R ifNotNull(T val, Function<T, R> op, R defaultVal) {
        return val == null ? defaultVal : op.apply(val);
    }
}