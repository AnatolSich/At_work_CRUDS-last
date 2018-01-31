package dao;

import util.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDao {

    private Connection connection;

    public TestDao() {
        this.connection = new ConnectDB().getConnection();
    }

    public String getNameById(int id){
        String name="";
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT name FROM users WHERE id=?");
            preparedStatement.setInt(1,id);
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
