package by.alex.kasperovich.servlet.person;

import by.alex.kasperovich.entity.Person;
import by.alex.kasperovich.service.PersonService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PersonListServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      req.setAttribute("message", 1337);
        PersonService personService = new PersonService();
        List<Person> people = personService.readAllPersons();

        req.setAttribute("personList", people);


        req.getRequestDispatcher("/pages/personListPage.jsp").forward(req, resp);
    }
}
