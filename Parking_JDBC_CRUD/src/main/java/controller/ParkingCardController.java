package controller;

import dao.OwnerDB;
import dao.ParkingCardDB;
import model.ParkingCard;
import service.CalculationsDB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static service.Constants.DATE_PATTERN;
import static util.Operations.*;

public class ParkingCardController extends HttpServlet {
    private ParkingCardDB parkingCardDB;
    private OwnerDB ownerDB;
    private CalculationsDB calculationsDB;

    private static String CREATE_PARKING_CARD = "/createParkingCard.jsp";
    private static String EDIT_PARKING_CARD = "/editParkingCard.jsp";
    private static String LIST_PARKING_CARDS = "/listParkingCards.jsp";
    private static String LIST_PARKING_CARDS_BY_CAR = "/listParkingCardsByCarNumber.jsp";
    private static String LIST_PARKING_CARDS_BY_OWNER = "/listParkingCardsByOwner.jsp";


    public ParkingCardController() {
        this.parkingCardDB = new ParkingCardDB();
        this.ownerDB = new OwnerDB();
        this.calculationsDB = new CalculationsDB();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionValue = req.getParameter("action");
        String view = "";

        if (CREATE.name().equalsIgnoreCase(actionValue)) {
            view = CREATE_PARKING_CARD;
        } else if (EDIT.name().equalsIgnoreCase(actionValue)) {
            int id = Integer.parseInt(req.getParameter("id"));
            req.setAttribute("parkingCard", parkingCardDB.getParkingCardById(id));
            view = EDIT_PARKING_CARD;
        } else if (DELETE.name().equalsIgnoreCase(actionValue)) {
            int id = Integer.parseInt(req.getParameter("id"));
            parkingCardDB.deleteParkingCardById(id);
            req.setAttribute("parkingCards", parkingCardDB.getAllparkingCards());
            view = LIST_PARKING_CARDS;
        } else if (LIST.name().equalsIgnoreCase(actionValue)) {
            req.setAttribute("parkingCards", parkingCardDB.getAllparkingCards());
            view = LIST_PARKING_CARDS;
        } else if (LIST_PARKING_CARDS_BY_CAR_NUMBER.name().equalsIgnoreCase(actionValue)) {
            String carNumber = req.getParameter("carNumber");
            req.setAttribute("carNumber", carNumber);
            req.setAttribute("parkingCards", parkingCardDB.getParkingCardsByCarNumber(carNumber));
            req.setAttribute("fullPayCheck", parkingCardDB.getFullPayCheckByCarNumber(carNumber));
            view = LIST_PARKING_CARDS_BY_CAR;
        } else if (LIST_PARKING_CARDS_BY_OWNER_ID.name().equalsIgnoreCase(actionValue)) {
            int ownerId = Integer.parseInt(req.getParameter("ownerId"));
            req.setAttribute("parkingCards", parkingCardDB.getParkingCardsByOwnerId(ownerId));
            req.setAttribute("owner", ownerDB.getOwnerById(ownerId));
            req.setAttribute("fullPayCheck", parkingCardDB.getFullPayCheckByOwnerId(ownerId));
            view = LIST_PARKING_CARDS_BY_OWNER;
        } else if (CALCULATE.name().equalsIgnoreCase(actionValue)) {
            String parkingCardId = req.getParameter("parkingCardId");
            if (parkingCardId == null || parkingCardId.isEmpty()) {
                calculationsDB.calculateAll();
            } else {
                calculationsDB.calculateByCardId(Integer.parseInt(parkingCardId));
            }
            req.setAttribute("parkingCards", parkingCardDB.getAllparkingCards());
            view = LIST_PARKING_CARDS;
        } else {
            throw new RuntimeException("Invalid parkingCardController request");
        }
        req.setAttribute("datePattern", DATE_PATTERN);
        RequestDispatcher dispatcher = req.getRequestDispatcher(view);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ParkingCard parkingCard = new ParkingCard();
        parkingCard.setCarNumber(req.getParameter("carNumber"));
        Date start = null;
        try {
            start = new SimpleDateFormat(DATE_PATTERN).parse(req.getParameter("start"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        parkingCard.setStart(start);

        String finishStr = req.getParameter("finish");
        if (finishStr != null & !finishStr.isEmpty() & !finishStr.equalsIgnoreCase(DATE_PATTERN)) {
            Date finish = null;
            try {
                finish = new SimpleDateFormat(DATE_PATTERN).parse(finishStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            parkingCard.setFinish(finish);
        }

        String period = req.getParameter("period");
        if (period != null & !period.isEmpty()) {
            parkingCard.setPeriod(Integer.valueOf(period));
        }
        String payCheck = req.getParameter("payCheck");
        if (payCheck != null & !payCheck.isEmpty()) {
            parkingCard.setPayCheck(Double.parseDouble(payCheck));
        }

        String id = req.getParameter("id");
        if (id.equalsIgnoreCase("Auto") || id == null || id.isEmpty()) {
            parkingCardDB.addParkingCard(parkingCard);
        } else {
            parkingCard.setId(Integer.parseInt(id));
            parkingCardDB.editParkingCard(parkingCard);
        }
        req.setAttribute("parkingCards", parkingCardDB.getAllparkingCards());
        req.setAttribute("datePattern", DATE_PATTERN);
        RequestDispatcher dispatcher = req.getRequestDispatcher(LIST_PARKING_CARDS);
        dispatcher.forward(req, resp);
    }
}
