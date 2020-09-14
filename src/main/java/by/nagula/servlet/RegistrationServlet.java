package by.nagula.servlet;

import by.nagula.entity.User;
import by.nagula.exception.UserNotUniqueException;
import by.nagula.service.UserServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(urlPatterns = "/reg")
public class RegistrationServlet extends HttpServlet {
    private static final String DUPLICATE_MESSAGE = "User is already exist";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/reg.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User user = new User(name,login,password);
        try {
            UserServiceImpl.getInstance((Connection) req.getSession().getAttribute("connection")).createUser(user);
        } catch (UserNotUniqueException ex){
            req.setAttribute("notUnique", DUPLICATE_MESSAGE);
            req.getRequestDispatcher("/reg.jsp").forward(req,resp);
        }

        resp.sendRedirect("/");
    }
}
