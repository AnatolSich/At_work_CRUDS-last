package controller;

import dao.TestDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestController extends HttpServlet {
    private TestDao testDao;

    public TestController() {
        this.testDao = new TestDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String actionValue=req.getParameter("action");
       if("GET_NAME".equalsIgnoreCase(actionValue)){
           String id = req.getParameter("id");
           req.setAttribute("name",testDao.getNameById(Integer.parseInt(id)));
       } else {
           throw new RuntimeException("Invalid request");
       }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/nameOut.jsp");
       dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
