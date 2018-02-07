package controller;

import crypto.KeyStoreInit;
import dao.CalculationsDB;
import dao.CarDB;
import dao.OwnerDB;
import dao.ParkingCardDB;

import javax.crypto.SecretKey;

public class ControllerToDaoConnector {
    static SecretKey secretKey = KeyStoreInit.getSecretKey();
    static OwnerDB ownerDB = new OwnerDB();
    static CarDB carDB = new CarDB();
    static ParkingCardDB parkingCardDB = new ParkingCardDB();
    static CalculationsDB calculationsDB = new CalculationsDB();
}
