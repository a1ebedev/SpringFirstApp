package ru.leqsle.dao;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import ru.leqsle.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/Person";
    private static final String USERNAME = "leqsle";
    private static final String PASSWORD = "123";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    static {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Person> index() throws SQLException {
        List<Person> people = new ArrayList<>();

        Statement statement = connection.createStatement();
        String SQL = "SELECT * FROM people";

        ResultSet resultSet = statement.executeQuery(SQL);

        while (resultSet.next()) {
            Person person = new Person();

            person.setId(resultSet.getInt("id"));
            person.setSurname(resultSet.getString("surname"));
            person.setSalary(resultSet.getInt("salary"));
            person.setPosition(resultSet.getString("position"));
            person.setDayOfBirth(resultSet.getInt("dayofbirth"));
            person.setMonthOfBirth(resultSet.getInt("monthofbirth"));
            person.setYearOfBirth(resultSet.getInt("yearofbirth"));

            people.add(person);
        }

        return people;
    }
}
