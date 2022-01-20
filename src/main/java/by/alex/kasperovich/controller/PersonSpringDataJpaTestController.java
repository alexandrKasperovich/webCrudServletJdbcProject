package by.alex.kasperovich.controller;

import by.alex.kasperovich.dao.PersonSpringDataJpaDao;
import by.alex.kasperovich.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/mvc/data/person")
public class PersonSpringDataJpaTestController {

    @Autowired
    PersonSpringDataJpaDao personSpringDataJpaDao;


    @GetMapping("/list/{nameLike}")
    public String getPersonListPageGet(Model model, @PathVariable ("nameLike") String nameLike) {
        String pattern = "%" + nameLike + "%";
        List<Person> personList = personSpringDataJpaDao.getPersonWithNameLike(pattern);
        model.addAttribute("personList", personList);
        return "personListPage";
    }

    @GetMapping("/list/{nameLike}/age/between/{fromAge}/{toAge}")
    public String getPersonListPageGet2(Model model,
                                        @PathVariable ("nameLike") String nameLike,
                                        @PathVariable ("fromAge") String fromAgeParam,
                                        @PathVariable ("toAge") String toAgeParam) {
        String pattern = "%" + nameLike + "%";
        int fromAge = Integer.parseInt(fromAgeParam);
        int toAge = Integer.parseInt(toAgeParam);
        List<Person> personList = personSpringDataJpaDao.getPersonWithNameLikeOrAgeBetween(pattern,fromAge,toAge);
        model.addAttribute("personList", personList);
        return "personListPage";
    }
}
