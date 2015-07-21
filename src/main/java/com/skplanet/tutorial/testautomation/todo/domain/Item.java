package com.skplanet.tutorial.testautomation.todo.domain;

import java.time.LocalDateTime;

public class Item {

    private long id;
    private boolean status = false;
    private LocalDateTime createdDate;
    private LocalDateTime doneDate;
    private String text;

    public Item(long id, boolean status, LocalDateTime createdDate,
            LocalDateTime doneDate, String text) {
        super();
        this.id = id;
        this.status = status;
        this.createdDate = createdDate;
        this.doneDate = doneDate;
        this.text = text;
    }

    public long getId()
    {
        return id;
    }

    public boolean isDone()
    {
        return status;
    }

    public LocalDateTime getCreatedDate()
    {
        return createdDate;
    }

    public LocalDateTime getDoneDate()
    {
        return doneDate;
    }

    public String getText()
    {
        return text;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Item other = (Item) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "Item [id=" + id + ", status=" + status + ", createdDate="
                + createdDate + ", doneDate=" + doneDate + ", text=" + text
                + "]";
    }
}