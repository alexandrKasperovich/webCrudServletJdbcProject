package by.alex.kasperovich.dao;

import by.alex.kasperovich.entity.Person;

import java.lang.module.Configuration;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonJdbcDao implements PersonDao {

    public static void main(String[] args) {
        PersonJdbcDao personJdbcDao = new PersonJdbcDao();
        personJdbcDao.createPerson(new Person("19999999", 99));
        personJdbcDao.deletePersonById(10);
        personJdbcDao.updatePerson(new Person(5, "00000", 1111));
        Person person = personJdbcDao.readPersonById(2);
        System.out.println(person);
        List<Person> people = personJdbcDao.readAllPerson();
        System.out.println(people);
    }

    @Override
    public long createPerson(Person person) {
        String name = person.getName();
        int age = person.getAge();
        String sql = "INSERT INTO `my_datebase`.`person` (`name_person`, `age_person`) VALUES ('" + name + "', " + age + ");";
        queryUpdate(sql);
        return 0;
    }

    @Override
    public Person readPersonById(long id) {
        String sql = "SELECT * FROM my_datebase.person where id_person = " + id + ";";
        List<Person> personList = queryRead(sql);
        Person person = new Person();
        if (personList.size() > 0) {
            person = personList.get(0);
        }
        return person;
    }

    @Override
    public List<Person> readAllPerson() {
        String sql = "SELECT * FROM my_datebase.person;";
        List<Person> personList = queryRead(sql);
        return personList;

    }

    @Override
    public void updatePerson(Person personUpdated) {
        long id = personUpdated.getId();
        int age = personUpdated.getAge();
        String name = personUpdated.getName();

        String sql = "UPDATE `my_datebase`.`person` SET `name_person` = '" + name
                + "', `age_person` = '" + age + "' WHERE (`id_person` = '" + id + "');";
        queryUpdate(sql);
    }

    @Override
    public void deletePersonById(long id) {
        String sql = "DELETE FROM `my_datebase`.`person` WHERE (`id_person` = '" + id + "');";
        queryUpdate(sql);
    }

    public void queryUpdate(String sql) {
        Connection connection = null;
        try {
            connection = MysqlJdbcUtil.getConnection();
            Statement statement = connection.createStatement();

            connection.setAutoCommit(false);
            statement.execute(sql);

            connection.commit();
            connection.rollback();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) { /* do nothing ...*/}
        } finally {
            MysqlJdbcUtil.closeStatementAndConnection();
        }
    }

    public List<Person> queryRead(String sql) {
        List<Person> personList = new ArrayList<>();

        try (Connection connection = MysqlJdbcUtil.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                String name = resultSet.getString("name_person");
                int age = resultSet.getInt(3);
                Person person = new Person(id, name, age);
                personList.add(person);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return personList;

    }
}
