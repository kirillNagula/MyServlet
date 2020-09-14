package by.nagula.servlet;

import by.nagula.entity.Operation;
import by.nagula.entity.User;
import by.nagula.exception.NoOperationException;
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

@WebServlet(urlPatterns = "/operations")
public class OperationsServlet extends HttpServlet {
    private static final String NO_OPERATIONS = "No operations on this user";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OperationService operationService = OperationServiceImpl.getInstance((Connection) req.getSession().getAttribute("connection"));
        User user = (User) req.getSession().getAttribute("user");
        if (operationService.isOperation(user.getId())){
            List<Operation> operations = operationService.getOperations(user.getId());
            req.setAttribute("operations", operations);
        } else {
            req.setAttribute("message", NO_OPERATIONS);
        }
        req.getRequestDispatcher("/operations.jsp").forward(req,resp);
    }
}
