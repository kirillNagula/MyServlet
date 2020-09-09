package by.nagula.servlet;

import by.nagula.entity.User;
import by.nagula.service.UserService;
import by.nagula.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(urlPatterns = "/account")
public class UpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/account.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserService userService = UserServiceImpl.getInstance((Connection) req.getSession().getAttribute("connection"));
        User user = (User) session.getAttribute("user");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        userService.update(new User(user.getId(), name, user.getLogin(), password));
        req.getSession().removeAttribute("user");
        req.getSession().setAttribute("user", userService.getUserByLogin(user.getLogin()));
        resp.sendRedirect("/");
    }
}
