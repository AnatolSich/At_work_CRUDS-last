package model;

import java.util.Date;

public class ParkingCard {
    int id;
    String carNumber;
    Date start;
    Date finish;
    int period;
    double payCheck;

    public ParkingCard(int id, String carNumber, Date start, Date finish, int period, double payCheck) {
        this.id = id;
        this.carNumber = carNumber;
        this.start = start;
        this.finish = finish;
        this.period = period;
        this.payCheck = payCheck;
    }

    public ParkingCard() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
        this.finish = finish;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public double getPayCheck() {
        return payCheck;
    }

    public void setPayCheck(double payCheck) {
        this.payCheck = payCheck;
    }
}
