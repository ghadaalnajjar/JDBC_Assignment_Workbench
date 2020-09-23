package org.example.todo;

import org.example.dbConnection.MySQLConnection;
import org.example.exception.MySQLConnectionException;
import org.example.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class CreatePeople implements People {

    @Override
    public Person create(Person person) {
        Person createPerson = new Person();
        String createQuery = "insert into todoit.person (first_name, last_name) values (?, ?);";

        try (
                Connection connection = MySQLConnection.mySQLGetConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(createQuery)) {
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                createPerson.setPersonId(resultSet.getInt("person_id"));
                createPerson.setFirstName(resultSet.getString("first_name"));
                createPerson.setLastName(resultSet.getString("last_name"));
            }
        } catch (SQLException | MySQLConnectionException e) {
            e.printStackTrace();
        }
        return createPerson;
    }

    @Override
    public Collection<Person> findAll() {
        Collection<Person> personCollection = new ArrayList<>();
        String findAllQuery = "select * from todoit.person;";

        try (
                Connection connection = MySQLConnection.mySQLGetConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(findAllQuery)) {
            while (resultSet.next()) {
                personCollection.add(new Person(resultSet.getInt("person_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name")));
            }
        } catch (SQLException | MySQLConnectionException e) {
            e.printStackTrace();
        }
        return personCollection;
    }
    // ghhhxfjdkfjdskfjdkfkd
    @Override
    public Person findById(int id) {
        Person person = new Person();
        String queryFindById = "select * from todoit.person where person_id = ?;";


        try (
                Connection connection = MySQLConnection.mySQLGetConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(queryFindById)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                person.setPersonId(resultSet.getInt("person_id"));
                person.setFirstName(resultSet.getString("first_name"));
                person.setLastName(resultSet.getString("last_name"));
            }

        } catch (SQLException | MySQLConnectionException e) {
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public Collection<Person> findByName(String name) {
        Collection<Person> personCollection = new ArrayList<>();
        String findByNameQuery = "Select * from todoit.person where concat(first_name , ' ' ,last_name) like '%?%';";

        try (
                Connection connection = MySQLConnection.mySQLGetConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(findByNameQuery)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                personCollection.add(new Person(resultSet.getInt("person_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name")));
            }
        } catch (SQLException | MySQLConnectionException e) {
            e.printStackTrace();
        }
        return personCollection;
    }

    @Override
    public Person update(Person person) {

        if (person.getPersonId() == 0) {
            throw new IllegalArgumentException("Exception you cannot UPDATE todoit.person because it is not yet existed");
        }
        Person updatePerson = new Person();
        String updatePersonQuery = "Update todoit.person set first_name = ?, last_name = ? where person_id = ?;";

        try (Connection connection = MySQLConnection.mySQLGetConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updatePersonQuery)) {
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setInt(3, person.getPersonId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                updatePerson.setPersonId(resultSet.getInt("person_id"));
                updatePerson.setFirstName(resultSet.getString("first_name"));
                updatePerson.setLastName(resultSet.getString("last_name"));
            }
        } catch (SQLException | MySQLConnectionException e) {
            e.printStackTrace();
        }
        return updatePerson;
    }

    @Override
    public boolean deleteById(int id) {
        if (id == 0) {
            throw new IllegalArgumentException("Exception you cannot DELETE todoit.person because it is not yet existed");
        }
        String deletePersonQuery = "Delete from todoit.person where person_id = ?; ";

        try (
                Connection connection = MySQLConnection.mySQLGetConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(deletePersonQuery)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException | MySQLConnectionException e) {
            e.printStackTrace();
        }
        return false;
    }
}
