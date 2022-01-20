package by.alex.kasperovich.servlet.person;

import by.alex.kasperovich.service.PersonService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PersonCreateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/personCreatePage.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newPersonNameParam = req.getParameter("NewPersonNameParam");
        String newPersonAgeParam = req.getParameter("NewPersonAgeParam");
        PersonService personService = new PersonService();
        personService.createNewPerson(newPersonNameParam,newPersonAgeParam);
        resp.sendRedirect("/webCrudServletJdbc/person/list");
    }
}
