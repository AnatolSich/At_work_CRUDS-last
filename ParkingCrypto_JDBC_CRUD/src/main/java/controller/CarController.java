package controller;

import dao.CarDB;
import model.Car;
import model.Owner;
import util.Operations;

import static controller.ControllerToDaoConnector.ownerDB;
import static controller.ControllerToDaoConnector.carDB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.Operations.*;

public class CarController extends HttpServlet {

    private static final String CREATE_CAR = "/createCar.jsp";
    private static final String EDIT_CAR = "/editCar.jsp";
    private static final String LIST_CARS = "/listCars.jsp";
    private static final String LIST_CARS_BY_OWNER = "/listCarsByOwnerId.jsp";
    private static final String CAR = "/car.jsp";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionValue = req.getParameter("action");
        String view = "";

        if (CREATE.name().equalsIgnoreCase(actionValue)) {
            int ownerId = Integer.parseInt(req.getParameter("ownerId"));
            Owner owner = ownerDB.getOwnerById(ownerId);
            req.setAttribute("owner", owner);
            view = CREATE_CAR;
        } else if (EDIT.name().equalsIgnoreCase(actionValue)) {
            int ownerId = Integer.parseInt(req.getParameter("ownerId"));
            Owner owner = ownerDB.getOwnerById(ownerId);
            req.setAttribute("owner", owner);
            String carNumber = req.getParameter("carNumber");
            req.setAttribute("oldCarNumber", carNumber);
            req.setAttribute("car", carDB.getCarByNumber(carNumber));
            view = EDIT_CAR;
        } else if (DELETE.name().equalsIgnoreCase(actionValue)) {
            String carNumber = req.getParameter("carNumber");
            carDB.deleteCarByNumber(carNumber);
            String list = req.getParameter("list");
            if ("CARS_BY_OWNER".equalsIgnoreCase(list)) {
                int ownerId = Integer.parseInt(req.getParameter("ownerId"));
                req.setAttribute("cars", carDB.getCarsByOwnerId(ownerId));
                String ownerName = ownerDB.getOwnerNameById(ownerId);
                req.setAttribute("ownerName", ownerName);
                view = LIST_CARS_BY_OWNER;
            } else if ("CARS".equalsIgnoreCase(list)) {
                req.setAttribute("cars", carDB.getAllCars());
                view = LIST_CARS;
            }
        } else if (LIST.name().equalsIgnoreCase(actionValue)) {
            req.setAttribute("cars", carDB.getAllCars());
            view = LIST_CARS;
        } else if (LIST_CARS_BY_OWNER_ID.name().equalsIgnoreCase(actionValue)) {
            int ownerId = Integer.parseInt(req.getParameter("ownerId"));
            req.setAttribute("cars", carDB.getCarsByOwnerId(ownerId));
            String ownerName = ownerDB.getOwnerNameById(ownerId);
            req.setAttribute("ownerName", ownerName);
            req.setAttribute("ownerId", ownerId);
            view = LIST_CARS_BY_OWNER;
        } else if (Operations.CAR.name().equalsIgnoreCase(actionValue)) {
            String carNumber = req.getParameter("carNumber");
            req.setAttribute("car", carDB.getCarByNumber(carNumber));
            view = CAR;
        } else {
            throw new RuntimeException("invalid carController request");
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher(view);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Car car = new Car();
        car.setCarNumber(req.getParameter("carNumber"));
        int ownerId = Integer.valueOf(req.getParameter("ownerId"));
        car.setOwnerId(ownerId);

        String creation = req.getParameter("creation");
        if (creation.equalsIgnoreCase("new")) {
            carDB.addCar(car);
        } else {
            carDB.editCar(creation, car);
        }
        req.setAttribute("cars", carDB.getCarsByOwnerId(ownerId));
        req.setAttribute("ownerId", ownerId);
        String ownerName = ownerDB.getOwnerNameById(ownerId);
        req.setAttribute("ownerName", ownerName);
        RequestDispatcher dispatcher = req.getRequestDispatcher(LIST_CARS_BY_OWNER);
        dispatcher.forward(req, resp);
    }
}
