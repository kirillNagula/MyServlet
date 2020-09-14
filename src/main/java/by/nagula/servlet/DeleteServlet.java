package by.nagula.servlet;

import by.nagula.entity.User;
import by.nagula.service.UserService;
import by.nagula.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(urlPatterns = "/delete")
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/account.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = UserServiceImpl.getInstance((Connection) req.getSession().getAttribute("connection"));
        User user = (User) req.getSession().getAttribute("user");
        userService.removeUser(user.getId());
        req.getSession().invalidate();
        resp.sendRedirect("/");
    }
}
