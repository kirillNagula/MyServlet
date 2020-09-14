package by.nagula.servlet;

import by.nagula.entity.User;
import by.nagula.exception.UserNotFoundException;
import by.nagula.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(urlPatterns = "/auth")
public class AuthorizationServlet extends HttpServlet {
    private static final String NO_USER = "This user not found";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/auth.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            User user = UserServiceImpl.getInstance((Connection) req.getSession().getAttribute("connection")).getUserByLogin(login);
            req.getSession().setAttribute("user", user);
        } catch (UserNotFoundException ex){
            req.setAttribute("noUser", NO_USER);
            req.getRequestDispatcher("/auth.jsp").forward(req,resp);
        }
        resp.sendRedirect("/");
    }
}

