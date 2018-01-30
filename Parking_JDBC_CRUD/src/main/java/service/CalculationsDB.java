package service;

import util.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import static service.Constants.TAX;

public class CalculationsDB {
    private Connection connection;

    public CalculationsDB() {
        this.connection = ConnectDB.getConnection();
    }

   /* public int calculatePeriod(Date start, Date finish){

    }*/

    public void calculateAll() {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE parking_cards SET period = finish-parking_cards.start,\n" +
                            "  payCheck=(finish-parking_cards.start)*" + TAX);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void calculateByCardId(int parkingCardId) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE parking_cards SET period = finish-parking_cards.start,\n" +
                            "payCheck=(finish-parking_cards.start)*" + TAX + "\n" +
                            "WHERE id=?");
            preparedStatement.setInt(1,parkingCardId);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
