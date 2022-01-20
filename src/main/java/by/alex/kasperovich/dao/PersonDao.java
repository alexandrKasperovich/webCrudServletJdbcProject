package by.alex.kasperovich.dao;

import by.alex.kasperovich.entity.Person;

import java.util.List;

public interface PersonDao {

    long createPerson(Person person);

    Person readPersonById(long id);

    List<Person> readAllPerson();

    void updatePerson(Person personUpdated);

    void deletePersonById(long id);

}
