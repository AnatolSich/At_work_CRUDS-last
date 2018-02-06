package controller;

import dao.CalculationsDB;
import dao.CarDB;
import dao.OwnerDB;
import dao.ParkingCardDB;

public class ControllerToDaoConnector {
    static OwnerDB ownerDB = new OwnerDB();
    static CarDB carDB = new CarDB();
    static ParkingCardDB parkingCardDB = new ParkingCardDB();
    static CalculationsDB calculationsDB = new CalculationsDB();
}
