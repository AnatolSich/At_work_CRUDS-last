package dao;

import util.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BmwDao {
    private Connection connection;

    public BmwDao() {
        this.connection = new ConnectDB().getConnection();
    }

    public String getModelNameById(int id) {
        String name = "";
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT name_BMW_model" +
                            "FROM spr_BMW_models" +
                            "WHERE id_BMV_model=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                name=resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }
}
