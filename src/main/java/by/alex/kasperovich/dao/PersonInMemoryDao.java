package by.alex.kasperovich.dao;

import by.alex.kasperovich.entity.Person;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class PersonInMemoryDao implements PersonDao {

    private static List<Person> personList = new ArrayList<>();
    ;

    static {
        PersonInMemoryDao personInMemoryDao = new PersonInMemoryDao();
        personList.add(new Person(personInMemoryDao.generateId(), "Bob", 22));
        personList.add(new Person(personInMemoryDao.generateId(), "Jam", 45));
        personList.add(new Person(personInMemoryDao.generateId(), "Dao", 32));
        personList.add(new Person(personInMemoryDao.generateId(), "Candy", 78));
        personList.add(new Person(personInMemoryDao.generateId(), "Alex", 16));
        personList.add(new Person("Jonny", 101));
        personList.add(new Person("Grigorych", 999));
    }

    private long generateId() {
        Long x = 1L;
        long y = 10_000;
        Random r = new Random();
        long generatedId = x + ((long) (r.nextDouble() * (y - x)));

        while (isExistInList(generatedId)) {
            generatedId = x + ((long) (r.nextDouble() * (y - x)));
        }
        return generatedId;
    }

    private boolean isExistInList(long generatedId) {
        for (Person person : personList) {
            if (person.getId() == generatedId) {
                return true;
            }
        }
        return false;
    }

    @Override
    public long createPerson(Person person) {
        person.setId(generateId());
        personList.add(person);
        return 0;
    }

    @Override
    public Person readPersonById(long id) {
        for (Person person : personList) {
            long idPerson = person.getId();
            if (idPerson == id) {
                return person;
            }
        }
        return null;
    }

    @Override
    public List<Person> readAllPerson() {
        return personList;
    }

    @Override
    public void updatePerson(Person personUpdated) {
        for (int i = 0; i < personList.size(); i++) {
            Person person = personList.get(i);
            long id = person.getId();
            if(id==personUpdated.getId()){
                person.setName(personUpdated.getName());
                person.setAge(personUpdated.getAge());
                break;
            }
        }
    }

    @Override
    public void deletePersonById(long id) {
        for (Person person : personList) {
            long idPerson = person.getId();
            if (idPerson == id) {
                personList.remove(person);
                break;
            }
        }
    }
}
