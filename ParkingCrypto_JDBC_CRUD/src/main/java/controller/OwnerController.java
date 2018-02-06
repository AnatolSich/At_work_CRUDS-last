package controller;

import model.Owner;
import util.Operations;
import static controller.ControllerToDaoConnector.ownerDB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.Operations.*;

public class OwnerController extends HttpServlet {

    private static final String CREATE_OWNER = "/createOwner.jsp";
    private static final String EDIT_OWNER = "/editOwner.jsp";
    private static final String LIST_OWNERS = "/listOwners.jsp";
    private static final String OWNER = "/owner.jsp";



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionValue = req.getParameter("action");
        String view = "";
        if (CREATE.name().equalsIgnoreCase(actionValue)) {
            view = CREATE_OWNER;
        } else if (EDIT.name().equalsIgnoreCase(actionValue)) {
            int ownerId = Integer.parseInt(req.getParameter("id").trim());
            req.setAttribute("owner", ownerDB.getOwnerById(ownerId));
            view = EDIT_OWNER;
        } else if (DELETE.name().equalsIgnoreCase(actionValue)) {
            int ownerId = Integer.parseInt(req.getParameter("id").trim());
            ownerDB.deleteOwnerById(ownerId);
            req.setAttribute("owners", ownerDB.getAllOwners());
            view = LIST_OWNERS;
        } else if (LIST.name().equalsIgnoreCase(actionValue)) {
            req.setAttribute("owners", ownerDB.getAllOwners());
            view = LIST_OWNERS;
        }
        else if (Operations.OWNER.name().equalsIgnoreCase(actionValue)) {
            int ownerId = Integer.parseInt(req.getParameter("ownerId"));
            req.setAttribute("owner",ownerDB.getOwnerById(ownerId));
            view = OWNER;
        }

        else {
            throw new RuntimeException("Invalid ownerController request");
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher(view);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Owner owner = new Owner();
        owner.setName(req.getParameter("name"));

        String actionValue = req.getParameter("action");
        if (CREATE.name().equalsIgnoreCase(actionValue)) {
            ownerDB.addOwner(owner);
        } else if (EDIT.name().equalsIgnoreCase(actionValue)) {
            String ownerId = req.getParameter("ownerId");
            owner.setId(Integer.parseInt(ownerId));
            ownerDB.editOwner(owner);
        }
        req.setAttribute("owners", ownerDB.getAllOwners());
        RequestDispatcher dispatcher = req.getRequestDispatcher(LIST_OWNERS);
        dispatcher.forward(req, resp);
    }
}
