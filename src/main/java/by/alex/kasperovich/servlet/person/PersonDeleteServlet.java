package by.alex.kasperovich.servlet.person;

import by.alex.kasperovich.service.PersonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/person/delete")
public class PersonDeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String deleteIdParam = req.getParameter("DeleteIdParam");
        PersonService personService = new PersonService();
        personService.deletePersonById(deleteIdParam);
        resp.sendRedirect("/webCrudServletJdbc/person/list");
    }
}
