package org.example.todo;

import org.example.model.Person;
import org.example.model.Todo;

import java.util.Collection;

public interface ToDo {

    Todo create (Todo todo);
    Collection<Todo> findAll();
    Todo findById (int todo);
    Collection<Todo> findByDoneStatus (boolean status);
    Collection<Todo> findByAssignee (int assignee);
    Collection<Todo> findByAssignee (Person person);
    Collection<Todo> findByUnassignedTodoItems();
    Todo update (Todo todo);
    boolean deleteById (int id);

}
