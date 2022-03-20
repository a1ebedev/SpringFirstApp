package ru.leqsle.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.leqsle.models.Person;

import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM people", new BeanPropertyRowMapper(Person.class));
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO people VALUES(1, ?, ?, ?, ?, ?, ?)", person.getSurname(), person.getSalary(),
                person.getPosition(), person.getDayOfBirth(), person.getMonthOfBirth(), person.getYearOfBirth());
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM people WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE people SET surname=?, salary=?, position=?, " +
                        "dayofbirth=?, monthofbirth=?, yearofbirth=? WHERE id=?", updatedPerson.getSurname(), updatedPerson.getSalary(),
                updatedPerson.getPosition(), updatedPerson.getDayOfBirth(), updatedPerson.getMonthOfBirth(),
                updatedPerson.getYearOfBirth(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM people WHERE id=?", id);
    }
}
