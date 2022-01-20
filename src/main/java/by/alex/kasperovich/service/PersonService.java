package by.alex.kasperovich.service;

import by.alex.kasperovich.dao.PersonDao;
import by.alex.kasperovich.dao.PersonHibernateDao;
import by.alex.kasperovich.dao.PersonInMemoryDao;
import by.alex.kasperovich.dao.PersonJdbcDao;
import by.alex.kasperovich.entity.Person;
import by.alex.kasperovich.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    //PersonDao personDao = new PersonInMemoryDao();
//    PersonDao personDao = new PersonJdbcDao();
//    PersonDao personDao = new PersonHibernateDao();

    @Autowired
    @Qualifier("personSpringDataJpaDao")
    PersonDao personDao;

    public List<Person> readAllPersons() {
        List<Person> people = personDao.readAllPerson();
        return people;
    }

    public void createNewPerson(String newPersonNameParam, String newPersonAgeParam) {
        int newPersonAgeParam1 = Integer.parseInt(newPersonAgeParam);
        Person person = new Person(newPersonNameParam, newPersonAgeParam1);
        personDao.createPerson(person);
    }

    public void deletePersonById(String deleteIdParam) {
        int id = Integer.parseInt(deleteIdParam);
        personDao.deletePersonById(id);
    }

    public void updatePerson(String updatedPersonIdParam, String updatedPersonNameParam, String updatedPersonAgeParam) {
        long id = Long.parseLong(updatedPersonIdParam);
        int age = Integer.parseInt(updatedPersonAgeParam);
        Person person = new Person(id,updatedPersonNameParam, age);
        personDao.updatePerson(person);
    }

    public Person readPersonById(String updateIdParam) {
        long i = Long.parseLong(updateIdParam);
        Person person = personDao.readPersonById(i);
        return person;
    }
}
