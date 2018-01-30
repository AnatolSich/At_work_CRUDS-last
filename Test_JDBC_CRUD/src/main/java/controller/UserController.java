package controller;

import dao.UserDao;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static util.ActionOperationEnum.*;

public class UserController extends HttpServlet {
    private static final String INSERT_OR_EDIT = "/editUser.jsp";
    private static final String USER_LIST = "/listUser.jsp";
    private UserDao userDao;


    public UserController() {
        this.userDao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionValue = req.getParameter("action");
        String view = "";

        if (DELETE.toString().equalsIgnoreCase(actionValue)) {
            int userIdValue = Integer.parseInt(req.getParameter("userId"));
            userDao.deleteUser(userIdValue);
            view = USER_LIST;
            req.setAttribute("users", userDao.getAllUsers());
        } else if (CREATE.toString().equalsIgnoreCase(actionValue)) {
            view = INSERT_OR_EDIT;
        } else if (EDIT.toString().equalsIgnoreCase(actionValue)) {
            int userIdValue = Integer.parseInt(req.getParameter("userId"));
            view = INSERT_OR_EDIT;
            req.setAttribute("user", userDao.getUserById(userIdValue));
        } else if (LIST.toString().equalsIgnoreCase(actionValue)) {
            view = USER_LIST;
            req.setAttribute("users", userDao.getAllUsers());
        } else {
            throw new RuntimeException("Invalid action");
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(view);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setFirstName(req.getParameter("firstName"));
        user.setLastName(req.getParameter("lastName"));
        user.setEmail(req.getParameter("email"));
        try {
            Date dob = new SimpleDateFormat("dd/MM/yyy").parse(req.getParameter("dob"));
            user.setDob(dob);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String id = req.getParameter("id");
        if(id==null || id.isEmpty()) {
            userDao.addUser(user);
        } else {
            user.setId(Integer.parseInt(id));
            userDao.updateUser(user);
        }
        req.setAttribute("users", userDao.getAllUsers());
        req.getRequestDispatcher(USER_LIST).forward(req,resp);
    }
}
