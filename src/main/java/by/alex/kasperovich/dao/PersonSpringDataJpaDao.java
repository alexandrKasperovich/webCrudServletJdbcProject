package by.alex.kasperovich.dao;


import by.alex.kasperovich.entity.Person;
import by.alex.kasperovich.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonSpringDataJpaDao implements PersonDao {

    @Autowired
    PersonRepository personRepository;

    @Override
    public long createPerson(Person person) {
        Person save = personRepository.save(person);
        return save.getId();
    }

    @Override
    public Person readPersonById(long id) {
        Optional<Person> byId = personRepository.findById(id);
        Person person = byId.orElse(new Person());
        return person;
    }

    @Override
    public List<Person> readAllPerson() {
        Iterable<Person> all = personRepository.findAll();
        return (List<Person>) all;
    }

    @Override
    public void updatePerson(Person personUpdated) {
        personRepository.save(personUpdated);
    }

    @Override
    public void deletePersonById(long id) {
        personRepository.deleteById(id);
    }

    public Person getPersonByAge(int age){
        Person byAge = personRepository.findByAge(age);
        return byAge;
    }

    public List<Person> getPersonWithNameLike(String pattern){
        List<Person> byNameLike = personRepository.findByNameLike(pattern);
        return byNameLike;
    }

    public List<Person> getPersonWithNameLikeOrAgeBetween(String pattern,int fromAge,int toAge){
        List<Person> byNameLike = personRepository.findByNameLikeOrAgeBetween(pattern,fromAge,toAge);
        return byNameLike;
    }
}
