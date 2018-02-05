package model;

public class Car {

    String carNumber;
    int ownerId;

    public Car(String carNumber, int ownerId) {
        this.carNumber = carNumber;
        this.ownerId = ownerId;

    }

    public Car() {
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
}
