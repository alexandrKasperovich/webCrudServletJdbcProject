package by.alex.kasperovich.controller;

import by.alex.kasperovich.entity.Person;
import by.alex.kasperovich.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/mvc/person")
public class PersonController {
    @Autowired
    public PersonService personService;

    @GetMapping("/list")
    public String getPersonListPageGet(Model model) {
        return getPersonListPage(model);
    }

    @PostMapping("/list")
    public String getPersonListPagePost(Model model) {
        return getPersonListPage(model);
    }

    private String getPersonListPage(Model model) {
        List<Person> personList = personService.readAllPersons();
        model.addAttribute("personList", personList);
        return "personListPage";
    }

    @GetMapping("/create")
    public String getPersonCreatePage() {
        return "personCreatePage";
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String getPersonCreatePage2(
            @RequestParam("NewPersonNameParam") String personName,
            @RequestParam("NewPersonAgeParam") String personAge) {
        personService.createNewPerson(personName, personAge);
        return "forward:/mvc/person/list";
    }


    @PostMapping("/delete")
    public String getPersonDeletePage(@RequestParam("DeleteIdParam") String idParam) {
        personService.deletePersonById(idParam);
        return "redirect:/mvc/person/list";
    }


    @GetMapping("/update")
    public String getPersonUpdatePage(Model model, @RequestParam("UpdateIdParam") String idParam) {
        Person person = personService.readPersonById(idParam);
        model.addAttribute("person", person);
        return "personUpdatePage";
    }

    @PostMapping("/update")
    public String getPersonUpdatePage(
            @RequestParam("UpdatedPersonIdParam") String updatedIdParam,
            @RequestParam("UpdatedPersonNameParam") String updateNameParam,
            @RequestParam("UpdatedPersonAgeParam") String updateAgeParam) {

        personService.updatePerson(updatedIdParam, updateNameParam, updateAgeParam);
        return "redirect:/mvc/person/list";
    }

    public static void main(String[] args) {

    }

}
