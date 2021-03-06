package by.nagula.servlet;

import by.nagula.entity.Operation;
import by.nagula.exception.NoListOperationException;
import by.nagula.service.OperationService;
import by.nagula.service.OperationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet(urlPatterns = "/logout")
public class LogOutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OperationService operationService = OperationServiceImpl.getInstance((Connection) req.getSession().getAttribute("connection"));
        try {
            operationService.setOperations((List<Operation>) req.getSession().getAttribute("operations"));
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        req.getSession().invalidate();
        resp.sendRedirect("/");
    }
}
