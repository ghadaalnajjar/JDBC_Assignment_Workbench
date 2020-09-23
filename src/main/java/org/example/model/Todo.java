package org.example.model;

/*
CREATE TABLE IF NOT EXISTS toDoIt (
todo_id INT NOT NULL AUTO_INCREMENT,
title VARCHAR(50) NULL,
description VARCHAR(1000) NULL,
deadline DATE NULL,
done TINYINT NULL DEFAULT 0,
assignee_id INT NULL,
 */

import java.time.LocalDate;
import java.util.Objects;

public class Todo {
    private int toDoId;
    private String title;
    private String description;
    private LocalDate deadLine;
    private boolean done;
    private int assigneeId;

    public Todo() {

    }

    public Todo(int toDoId, String title, String description, LocalDate deadLine, boolean done, int assigneeId) {
        this.toDoId = toDoId;
        this.title = title;
        this.description = description;
        this.deadLine = deadLine;
        this.done = done;
        this.assigneeId = assigneeId;
    }

    public Todo(String title, String description, LocalDate deadLine, boolean done, int assigneeId) {
        this.title = title;
        this.description = description;
        this.deadLine = deadLine;
        this.done = done;
        this.assigneeId = assigneeId;
    }

    public int getToDoId() {
        return toDoId;
    }

    public void setToDoId(int toDoId) {
        this.toDoId = toDoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDate deadLine) {
        this.deadLine = deadLine;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public int getAssignee() {
        return assigneeId;
    }

    public void setAssignee(int assignee) {
        this.assigneeId = assignee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof org.example.model.Todo)) return false;
        org.example.model.Todo todoItem = (org.example.model.Todo) o;
        return toDoId == todoItem.toDoId &&
                done == todoItem.done &&
                assigneeId == todoItem.assigneeId &&
                Objects.equals(title, todoItem.title) &&
                Objects.equals(description, todoItem.description) &&
                Objects.equals(deadLine, todoItem.deadLine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(toDoId, title, description, deadLine, done, assigneeId);
    }

    @Override
    public String toString() {
        return "Todo{" +
                "toDoId=" + toDoId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadLine=" + deadLine +
                ", done=" + done +
                ", assigneeId=" + assigneeId +
                '}';
    }
}
