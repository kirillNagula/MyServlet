package by.nagula.servlet;

import by.nagula.entity.User;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/aut")
public class AuthorizationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        List<User> users = (List<User>) req.getServletContext().getAttribute("users");

        for (User user : users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                req.getSession().setAttribute("user", user);
            }

        }
    }
}

