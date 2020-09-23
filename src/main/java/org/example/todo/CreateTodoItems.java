package org.example.todo;

import org.example.dbConnection.MySQLConnection;
import org.example.exception.MySQLConnectionException;
import org.example.model.Person;
import org.example.model.Todo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class CreateTodoItems implements ToDo {

    @Override
    public Todo create(Todo todo) {
        Todo createTodo = new Todo();
        String createQuery = " insert into todoit.todo_item (title, description, deadLine, done, assignee_id) values (? ,?, ?, ?, ?);";
        try (
                Connection connection = MySQLConnection.mySQLGetConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(createQuery)) {
            while (resultSet.next()) {
                createTodo.setToDoId(resultSet.getInt("todo_id"));
                createTodo.setTitle(resultSet.getString("title"));
                createTodo.setDescription(resultSet.getString("description"));
                createTodo.setDeadLine(LocalDate.parse(resultSet.getDate("deadLine").toString()));
                createTodo.setDone(resultSet.getBoolean("done"));
                createTodo.setAssignee(resultSet.getInt("assignee_id"));
            }
        } catch (SQLException | MySQLConnectionException e) {
            e.printStackTrace();
        }
        return createTodo;
    }

    @Override
    public Collection<Todo> findAll() {
        Collection<Todo> todoCollection = new ArrayList<>();
        String findAllQuery = "select * from todoit.todo_item;";

        try (
                Connection connection = MySQLConnection.mySQLGetConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(findAllQuery)) {
            while (resultSet.next()) {
                todoCollection.add(new Todo(resultSet.getInt("todo_id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        LocalDate.parse(resultSet.getDate("deadline").toString()),
                        resultSet.getBoolean("done"),
                        resultSet.getInt("assignee_id")));
            }
        } catch (SQLException | MySQLConnectionException e) {
            e.printStackTrace();
        }
        return todoCollection;
    }

    @Override
    public Todo findById(int todo) {
        Todo toDo = new Todo();
        String queryFindById = "select * from todoit.todo_item where todo_id = ?;";

        try (
                Connection connection = MySQLConnection.mySQLGetConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(queryFindById)) {
            preparedStatement.setInt(1, todo);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                toDo.setToDoId(resultSet.getInt("todo_id"));
                toDo.setTitle(resultSet.getString("title"));
                toDo.setDescription(resultSet.getString("description"));
                toDo.setDeadLine(LocalDate.parse(resultSet.getDate("deadline").toString()));
                toDo.setDone(resultSet.getBoolean("done"));
                toDo.setAssignee(resultSet.getInt("assignee_id"));
            }
        } catch (SQLException | MySQLConnectionException e) {
            e.printStackTrace();
        }
        return toDo;
    }

    @Override
    public Collection<Todo> findByDoneStatus(boolean status) {
        Collection<Todo> todoCollection = new ArrayList<>();
        String queryFindByAssignee = "Select * from todoit.todo_item where done = ?;";

        try (
                Connection connection = MySQLConnection.mySQLGetConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(queryFindByAssignee)) {
            preparedStatement.setBoolean(1, status);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                todoCollection.add(new Todo(resultSet.getInt("todo_id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        LocalDate.parse(resultSet.getDate("deadline").toString()),
                        resultSet.getBoolean("done"),
                        resultSet.getInt("assignee_id")));
            }
        } catch (SQLException | MySQLConnectionException e) {
            e.printStackTrace();
        }
        return todoCollection;
    }

    @Override
    public Collection<Todo> findByAssignee(int assignee) {
        Collection<Todo> todoCollection = new ArrayList<>();
        String queryFindByAssignee = "Select * from todoit.todo_item where assignee_id = ?;";

        try (
                Connection connection = MySQLConnection.mySQLGetConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(queryFindByAssignee)) {
            preparedStatement.setInt(1, assignee);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                todoCollection.add(new Todo(resultSet.getInt("todo_id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        LocalDate.parse(resultSet.getDate("deadline").toString()),
                        resultSet.getBoolean("done"),
                        resultSet.getInt("assignee_id")));
            }
        } catch (SQLException | MySQLConnectionException e) {
            e.printStackTrace();
        }
        return todoCollection;
    }

    @Override
    public Collection<Todo> findByAssignee(Person person) {
        Collection<Todo> todoCollection = new ArrayList<>();
        String queryFindByAssignee = "Select * from todoit.todo_item where assignee_id = ?;";

        try (
                Connection connection = MySQLConnection.mySQLGetConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(queryFindByAssignee)) {
            preparedStatement.setInt(1, person.getPersonId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                todoCollection.add(new Todo(resultSet.getInt("todo_id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        LocalDate.parse(resultSet.getDate("deadline").toString()),
                        resultSet.getBoolean("done"),
                        resultSet.getInt("assignee_id")));
            }
        } catch (SQLException | MySQLConnectionException e) {
            e.printStackTrace();
        }
        return todoCollection;
    }

    @Override
    public Collection<Todo> findByUnassignedTodoItems() {

        Collection<Todo> todoCollection = new ArrayList<>();
        String queryFindByAssignee = "Select * from todoit.todo_item where assignee_id is null;";

        try (
                Connection connection = MySQLConnection.mySQLGetConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(queryFindByAssignee)) {
            while (resultSet.next()) {
                todoCollection.add(new Todo(resultSet.getInt("todo_id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        LocalDate.parse(resultSet.getDate("deadline").toString()),
                        resultSet.getBoolean("done"),
                        resultSet.getInt("assignee_id")));
            }
        } catch (SQLException | MySQLConnectionException e) {
            e.printStackTrace();
        }
        return todoCollection;
    }

    @Override
    public Todo update(Todo todo) {
        if (todo.getToDoId() == 0) {
            throw new IllegalArgumentException("Exception you cannot UPDATE because it is not yet existed ");
        }
        Todo updateTodo = new Todo();
        String updateQuery = " Update todoit.todo_item set title = ?, description = ?, deadline = ?, done = ?, assignee_id = ?;";

        try (
                Connection connection = MySQLConnection.mySQLGetConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, todo.getTitle());
            preparedStatement.setString(2, todo.getDescription());
            preparedStatement.setDate(3, Date.valueOf(LocalDate.parse(todo.getDeadLine().toString())));
            preparedStatement.setBoolean(4, todo.isDone());
            preparedStatement.setInt(5, todo.getAssignee());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                updateTodo.setToDoId(resultSet.getInt("todo_id"));
                updateTodo.setTitle(resultSet.getString("title"));
                updateTodo.setDescription(resultSet.getString("description"));
                updateTodo.setDeadLine(LocalDate.parse(resultSet.getDate("deadline").toString()));
                updateTodo.setDone(resultSet.getBoolean("done"));
                updateTodo.setAssignee(resultSet.getInt("assignee_id"));
            }
        } catch (SQLException | MySQLConnectionException e) {
            e.printStackTrace();
        }
        return updateTodo;
    }

    @Override
    public boolean deleteById(int id) {
        if (id == 0) {
            throw new IllegalArgumentException("Exception you cannot UPDATE because it is not yet existed ");
        }
        String deleteQuery = "Delete from todoit.todo_item where todo_id = ?;";

        try (
                Connection connection = MySQLConnection.mySQLGetConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException | MySQLConnectionException e) {
            e.printStackTrace();
        }
        return false;
    }
}
