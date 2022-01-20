package by.alex.kasperovich.repository;

import by.alex.kasperovich.entity.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person,Long> {

    Person findByAge(int age);

    List<Person> findByNameLike(String pattern);

    List<Person> findByNameLikeOrAgeBetween(String pattern,int fromAge,int toAge);
    
}
