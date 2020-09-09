package by.nagula.servlet;

import by.nagula.exception.DivisionByZeroException;
import by.nagula.service.CalcService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        resp.setContentType("text/html;charset=UTF-8");
        String num1 = req.getParameter("num1");
        String num2 = req.getParameter("num2");
        String type = req.getParameter("type");
        double result = 0;
        try {
            result = calcService.calc(Double.parseDouble(num1), Double.parseDouble(num2), type);
            req.setAttribute("result", result);
            req.getRequestDispatcher("/calc.jsp").forward(req,resp);
        } catch (DivisionByZeroException e) {
            resp.getWriter().println("<h1>" + e.getMessage() + "</h1>");
        }
    }
}
