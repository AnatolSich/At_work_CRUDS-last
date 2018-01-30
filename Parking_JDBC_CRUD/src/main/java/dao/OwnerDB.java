package dao;

import model.Owner;
import util.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OwnerDB {
    private Connection connection;

    public OwnerDB() {
        this.connection = ConnectDB.getConnection();
    }

    public List<Owner> getAllOwners() {
        List<Owner> list = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM owners ORDER BY id");
            ResultSet resultSet = preparedStatement.executeQuery();
            while ((resultSet.next())) {
                Owner owner = new Owner(resultSet.getInt(1), resultSet.getString(2));
                list.add(owner);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Owner getOwnerById(int id) {
        Owner owner = new Owner();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM owners WHERE id=?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while ((resultSet.next())) {
               owner.setId(resultSet.getInt(1));
               owner.setName(resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return owner;
    }

    public void addOwner(Owner owner){
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO owners (name) VALUES (?)");
            preparedStatement.setString(1,owner.getName());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editOwner(Owner owner){
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE owners SET name=? WHERE id=?");
            preparedStatement.setString(1,owner.getName());
            preparedStatement.setInt(2,owner.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOwnerById(int id){
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM owners WHERE id=?");
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
