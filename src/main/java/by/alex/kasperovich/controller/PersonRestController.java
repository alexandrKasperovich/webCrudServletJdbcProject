package by.alex.kasperovich.controller;

import by.alex.kasperovich.dto.PersonDto;
import by.alex.kasperovich.entity.Person;
import by.alex.kasperovich.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/person")
public class PersonRestController {

    @Autowired
    public PersonService personService;

    @GetMapping("/get1")
    public String returnStringValue(){
        return "moloko";
    }

    @RequestMapping(value = "/get2", method = RequestMethod.GET)
    public int returnIntValue(){
        return 1;
    }

    @GetMapping
    public List<Person> getPersonList() {
        List<Person> personList = personService.readAllPersons();
        return personList;
    }

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable("id") String idFromUrl){
        Person person = personService.readPersonById(idFromUrl);
        return person;
    }

    @DeleteMapping("/{id}")
    public void deletePersonById (@PathVariable("id") String idFromUrl){
        personService.deletePersonById(idFromUrl);
    }

    @PostMapping
    public void createPerson(
            @RequestParam("NewPersonNameParam") String personName,
            @RequestParam("NewPersonAgeParam") String personAge) {
        personService.createNewPerson(personName, personAge);
    }

    @PostMapping("/create")
    public void createPerson2(@RequestBody PersonDto personDto) {
        String id = personDto.getId();
        String age = personDto.getAge();
        String name = personDto.getName();

        personService.createNewPerson(name,age);
    }

    @PutMapping
    public void updateNewPerson(@RequestBody PersonDto personDto) {
        String id = personDto.getId();
        String age = personDto.getAge();
        String name = personDto.getName();

        personService.updatePerson(id,name,age);
    }
}
