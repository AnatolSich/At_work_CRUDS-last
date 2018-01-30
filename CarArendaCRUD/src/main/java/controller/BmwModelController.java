package controller;

import dao.BmwDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BmwModelController extends HttpServlet {
    BmwDao bmwDao;

    public BmwModelController() {
        this.bmwDao = new BmwDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String actionValue = req.getParameter("action");
       String view = "";

       if("GET_MODEL".equalsIgnoreCase(actionValue)){
           int id = Integer.parseInt(req.getParameter("id"));
           req.setAttribute("name",bmwDao.getModelNameById(id));
           view="/output.jsp";
       }
       else {
           throw new RuntimeException("Invalid request");
       }
        RequestDispatcher dispatcher = req.getRequestDispatcher(view);
       dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
