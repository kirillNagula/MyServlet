package by.nagula.servlet;

import by.nagula.entity.Operation;
import by.nagula.entity.User;
import by.nagula.exception.DivisionByZeroException;
import by.nagula.service.CalcService;
import by.nagula.service.OperationService;
import by.nagula.service.OperationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/calculation")
public class CalcServlet extends HttpServlet {
    private CalcService calcService = new CalcService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("Calc", "Calc");
        req.getRequestDispatcher("/calc.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OperationService operationService = OperationServiceImpl.getInstance((Connection) req.getSession().getAttribute("connection"));
        resp.setContentType("text/html;charset=UTF-8");
        String num1 = req.getParameter("num1");
        String num2 = req.getParameter("num2");
        String type = req.getParameter("type");
        double result = 0;
        User user = (User) req.getSession().getAttribute("user");
        List<Operation> operations = (List<Operation>) req.getSession().getAttribute("list");
        try {
            result = calcService.calc(Double.parseDouble(num1), Double.parseDouble(num2), type);
            req.setAttribute("result", result);
            operations.add(new Operation(user.getId(), Date.valueOf(LocalDate.now()), type, Double.parseDouble(num1), Double.parseDouble(num2), result));
            req.getSession().setAttribute("operations", operations);
            req.getRequestDispatcher("/calc.jsp").forward(req,resp);
        } catch (DivisionByZeroException e) {
            resp.getWriter().println("<h1>" + e.getMessage() + "</h1>");
        }
    }
}
