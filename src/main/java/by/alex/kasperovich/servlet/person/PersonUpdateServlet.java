package by.alex.kasperovich.servlet.person;

import by.alex.kasperovich.entity.Person;
import by.alex.kasperovich.service.PersonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/person/update")
public class PersonUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String updateIdParam = req.getParameter("UpdateIdParam");
        PersonService personService = new PersonService();
        Person person = personService.readPersonById(updateIdParam);
        req.setAttribute("person",person);
        req.getRequestDispatcher("/pages/personUpdatePage.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String updatedPersonNameParam = req.getParameter("UpdatedPersonNameParam");
        String updatedPersonIdParam = req.getParameter("UpdatedPersonIdParam");
        String updatedPersonAgeParam = req.getParameter("UpdatedPersonAgeParam");
        PersonService personService = new PersonService();
        personService.updatePerson(updatedPersonIdParam,updatedPersonNameParam,updatedPersonAgeParam);

        resp.sendRedirect("/webCrudServletJdbc/person/list");
    }
}
